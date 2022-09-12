package pl.mk.expensesimporter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

    public ExpenseData parseCardPayment(String emailBody) {
        Document parse = Jsoup.parse(emailBody);
        Element body = parse.body();
        Elements boldElements = body.getElementsByTag("b");
        String priceText = boldElements.get(1).text();
        String date = boldElements.get(2).text();
        String shop = boldElements.get(3).text().trim();

        priceText = priceText.replaceAll("-", "").replaceAll(" PLN", "");

        return new ExpenseData(fixShopName(shop), priceText, date, "");
    }

    public ExpenseData parseChargePayment(String emailBody) {
        Document parse = Jsoup.parse(emailBody);
        Element body = parse.body();
        Elements boldElements = body.getElementsByTag("b");

        if (boldElements.size() == 10) {
            // probably transfer
            String priceText = boldElements.get(4).text();
            String date = boldElements.get(6).text();
            String shop = boldElements.get(3).text().trim();
            String title = boldElements.get(5).text().trim();

            priceText = priceText.replaceAll("-", "").replaceAll(" PLN", "");

            return new ExpenseData(shop, priceText, date, title);
        } else {
            // probably BLIK
            String priceText = boldElements.get(2).text();
            String date = boldElements.get(4).text();
            String title = boldElements.get(3).text().trim().replace("Zakup ", "");

            priceText = priceText.replaceAll("-", "").replaceAll(" PLN", "");

            return new ExpenseData("?", priceText, date, title);
        }
    }

    private String fixShopName(String originalName) {
        return switch (originalName) {
            case "KAUFLAND WROCLAW" -> "Kaufland Legnicka";
            case "JMP S.A. BIEDRONKA 446 WROCLAW" -> "Biedronka Bystrzycka";
            case "JMP S.A. BIEDRONKA 580 WROCLAW" -> "Biedronka Legnicka";
            case "JMP S.A. BIEDRONKA 1112 WROCLAW" -> "Biedronka Popowicka";
            case "JMP S.A. BIEDRONKA 3384 WROCLAW" -> "Biedronka Kozanowska";
            case "JMP S.A. BIEDRONKA 4106 GLOGOWEK" -> "Biedronka Głogówek";
            case "JMP S.A. BIEDRONKA 5807 WROCLAW" -> "Biedronka Legnicka";
            case "SKLEP LIDL 1941 WROCLAW" -> "LIDL Braniborska";
            case "ZYGULA WROCLAW 12 WROCLAW" -> "Zyguła";
            case "PEPCO 1839 WROCLAW 12 POP WROCLAW" -> "Pepco";
            default -> originalName;
        };
    }

}
