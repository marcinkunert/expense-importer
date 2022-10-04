package pl.mk.expensesimporter;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.model.Thread;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.common.io.BaseEncoding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

public class ExpensesImporter {

    private static final String SPREADSHEET_ID = "1tCmk4VmwLkBwybx4i4yiPjAdu49UHXrtZJp8L8cOaWs";
    private static final String APPLICATION_NAME = "Expenses Importer";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String USER_ID = "me";
    private static final String LABEL_ID_WYDATEK = "Label_1245183208216039034";
    public static final List<String> REQUIRED_AUTH_SCOPES = List.of(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_MODIFY, SheetsScopes.SPREADSHEETS);

    private final NetHttpTransport httpTransport;
    private final Gmail gmailService;
    private final Sheets sheetsService;
    private final Parser parser;

    public ExpensesImporter() throws IOException, GeneralSecurityException {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        gmailService = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
        sheetsService = new Sheets.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
        parser = new Parser();
    }

    public void run() throws IOException {
        ListThreadsResponse allInboxThreads = gmailService.users()
                .threads()
                .list(USER_ID)
                .setLabelIds(List.of("INBOX"))
                .execute();

        List<Thread> threads = allInboxThreads.getThreads();
        if (threads == null || threads.isEmpty()) {
            System.out.println("No mails to process");
            return;
        }
        Collections.reverse(threads);
        for (Thread thread : threads) {
            String snippet = thread.getSnippet();
            if (snippet.contains("W związku z transakcją ZAKUPY na Twojej karcie debetowej zostało zablokowane")) {
                handleMessage(thread, true);
            } else if (snippet.contains("Stan Twojego konta zmniejszył się o")) {
                handleMessage(thread, false);
            }
        }
    }

    private void handleMessage(Thread thread, boolean cardPayment) throws IOException {
        System.out.println(thread);
        Thread threadWithMessages = gmailService.users().threads().get(USER_ID, thread.getId()).execute();
        for (Message message : threadWithMessages.getMessages()) {
            String emailBody = new String(BaseEncoding.base64Url().decode(message.getPayload().getBody().getData()));
            ExpenseData expenseData;
            if (cardPayment) {
                expenseData = parser.parseCardPayment(emailBody);
            } else {
                expenseData = parser.parseChargePayment(emailBody);
            }
            insertIntoSpreadsheet(expenseData);
            System.out.println(expenseData);
            moveThreadToHandledDirectory(message);
        }
        System.out.println(threadWithMessages);
    }

    private void moveThreadToHandledDirectory(Message message) throws IOException {
        ModifyMessageRequest modifyMessageRequest = new ModifyMessageRequest();
        modifyMessageRequest.setAddLabelIds(List.of(LABEL_ID_WYDATEK));
        modifyMessageRequest.setRemoveLabelIds(List.of("INBOX"));
        gmailService.users().messages().modify(USER_ID, message.getId(), modifyMessageRequest).execute();
    }

    private void insertIntoSpreadsheet(ExpenseData expenseData) throws IOException {
        ValueRange valueRange = new ValueRange();

        List<List<Object>> rows = List.of(
                List.of(expenseData.date(), expenseData.shop(), "?", "?", expenseData.amount(), expenseData.title())
        );

        valueRange.setValues(rows);

        String yearMonth = YearMonth.now().toString();

        sheetsService.spreadsheets().values().append(SPREADSHEET_ID, yearMonth + "!A2:F9999", valueRange)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }

    private Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream in = ExpensesImporter.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Brak pliku: " + CREDENTIALS_FILE_PATH + " Umieść go w src/main/resources" + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, REQUIRED_AUTH_SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private void printLabels() throws IOException {
        ListLabelsResponse listResponse = gmailService.users().labels().list(USER_ID).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.isEmpty()) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName() + " id: " + label.getId());
            }
        }
    }
}
