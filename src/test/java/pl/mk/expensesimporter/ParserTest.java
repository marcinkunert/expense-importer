package pl.mk.expensesimporter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ParserTest {

    private Parser parser = new Parser();

    @Test
    public void shouldParseCardPayment() {
        // given
        String emailBody = """
                <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
                "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                                
                <html xmlns="http://www.w3.org/1999/xhtml" lang="pl" xml:lang="pl" style="background: #f3f3f3 !important;">
                  <head>
                    <link rel="stylesheet" type="text/css" href="../css/app.css">
                    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
                    <meta name="viewport" content="width=device-width">
                    <style>@media only screen {
                  html {
                    min-height: 100%;
                    background: #f3f3f3;
                  }
                }
                                
                @media only screen and (max-width: 830px) {
                  table.body table.container .show-for-large {
                    display: none !important;
                    width: 0;
                    mso-hide: all;
                    overflow: hidden;
                  }
                }
                                
                @media only screen and (max-width: 830px) {
                  table.body img {
                    width: auto;
                    height: auto;
                  }
                                
                  table.body center {
                    min-width: 0 !important;
                  }
                                
                  table.body .container {
                    width: 100% !important;
                  }
                                
                  table.body .columns {
                    height: auto !important;
                    -moz-box-sizing: border-box;
                    -webkit-box-sizing: border-box;
                    box-sizing: border-box;
                    padding-left: 30px !important;
                    padding-right: 30px !important;
                  }
                                
                  table.body .columns .columns {
                    padding-left: 0 !important;
                    padding-right: 0 !important;
                  }
                                
                  th.small-1 {
                    display: inline-block !important;
                    width: 6.25% !important;
                  }
                                
                  th.small-2 {
                    display: inline-block !important;
                    width: 12.5% !important;
                  }
                                
                  th.small-3 {
                    display: inline-block !important;
                    width: 18.75% !important;
                  }
                                
                  th.small-6 {
                    display: inline-block !important;
                    width: 37.5% !important;
                  }
                                
                  th.small-9 {
                    display: inline-block !important;
                    width: 56.25% !important;
                  }
                                
                  th.small-10 {
                    display: inline-block !important;
                    width: 62.5% !important;
                  }
                                
                  th.small-12 {
                    display: inline-block !important;
                    width: 75% !important;
                  }
                                
                  th.small-14 {
                    display: inline-block !important;
                    width: 87.5% !important;
                  }
                                
                  th.small-16 {
                    display: inline-block !important;
                    width: 100% !important;
                  }
                }
                                
                @media screen {
                  p,
                  li {
                    font-family: 'Open Sans', Arial, sans-serif !important;
                  }
                }</style>
                  </head>
                  <body style="-moz-box-sizing: border-box; -ms-text-size-adjust: 100%; -webkit-box-sizing: border-box; -webkit-text-size-adjust: 100%; Margin: 0; background: #f3f3f3 !important; box-sizing: border-box; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; min-width: 100%; padding: 0; text-align: left; width: 100% !important;">
                    <span class="preheader" style="color: #f3f3f3; display: none !important; font-size: 1px; line-height: 1px; max-height: 0px; max-width: 0px; mso-hide: all !important; opacity: 0; overflow: hidden; visibility: hidden;">Santander Bank Polska S.A.</span>
                    <table class="body" style="Margin: 0; background: #f3f3f3 !important; border-collapse: collapse; border-spacing: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; height: 100%; line-height: 1.71; margin: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                      <tr style="padding: 0; text-align: left; vertical-align: top;">
                        <td class="center" align="center" valign="top" style="-moz-hyphens: auto; -webkit-hyphens: auto; Margin: 0; border-collapse: collapse !important; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; hyphens: auto; line-height: 1.71; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word;">
                          <center data-parsed style="min-width: 800px; width: 100%;">
                              <table align="center" class="container float-center" style="Margin: 0 auto; background: #ffffff; border-collapse: collapse; border-spacing: 0; float: none; margin: 0 auto; margin-left: 0 !important; margin-right: 0 !important; padding: 0; text-align: center; vertical-align: top; width: 800px;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <td style="-moz-hyphens: auto; -webkit-hyphens: auto; Margin: 0; border-collapse: collapse !important; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; hyphens: auto; line-height: 1.71; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word;">
                				<table class="spacer" style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <td height="30pxpx" style="-moz-hyphens: auto; -webkit-hyphens: auto; Margin: 0; border-collapse: collapse !important; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 30pxpx; font-weight: normal; hyphens: auto; line-height: 30pxpx; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word;">&#xA0;</td></tr></tbody></table>\s
                				<table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                				  <th class="small-16 large-14 columns first last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 30px; padding-right: 30px; text-align: left; width: 670px;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                				    <h2 style="Margin: 0; Margin-bottom: 0px; color: #ec0000; font-family: Arial, sans-serif; font-size: 30px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 90px; padding: 0; text-align: left; word-wrap: normal;">
                				      <img src="https://static3.santander.pl/asset/L/o/g/Logo-Santander_86969.png" alt="Santander Bank Polska S.A." style="-ms-interpolation-mode: bicubic; clear: both; display: block; max-width: 100%; outline: none; text-decoration: none; width: auto;">
                				    </h2>
                				  </th></tr></table></th>
                				</tr></tbody></table>
                                <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                  <th class="small-16 large-16 columns first last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 30px; padding-right: 30px; text-align: left; width: 770px;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"><h6 style="Margin: 0; Margin-bottom: 0px; color: inherit; font-family: Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left; word-wrap: normal;">W związku z transakcją ZAKUPY na Twojej karcie debetowej zostało zablokowane -22,52 PLN.
                <br>
                <hr style="border-color: #ebf4f7; border-style: solid;">
                <br>
                <p style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                  Szczegóły:
                </p>
                <br>
                <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                    <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                    <th class="small-6 large-3 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 18.75%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left; word-break: break-word;">
                        Karta:
                        </p>
                    </th></tr></table></th>
                    <th class="small-10 large-13 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 81.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <b>
                        Dopasowana Mastercard 5391
                        </b>
                    </th></tr></table></th>
                </tr></tbody></table>  <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                    <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                    <th class="small-6 large-3 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 18.75%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left; word-break: break-word;">
                        Ile:
                        </p>
                    </th></tr></table></th>
                    <th class="small-10 large-13 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 81.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <b>
                        -22,52 PLN
                        </b>
                    </th></tr></table></th>
                </tr></tbody></table>  <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                    <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                    <th class="small-6 large-3 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 18.75%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left; word-break: break-word;">
                        Kiedy:
                        </p>
                    </th></tr></table></th>
                    <th class="small-10 large-13 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 81.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <b>
                        13-08-2022
                        </b>
                    </th></tr></table></th>
                </tr></tbody></table>  <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                    <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                    <th class="small-6 large-3 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 18.75%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left; word-break: break-word;">
                        Gdzie:
                        </p>
                    </th></tr></table></th>
                    <th class="small-10 large-13 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 81.25%;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        <b>
                        KAUFLAND WROCLAW
                        </b>
                    </th></tr></table></th>
                </tr></tbody></table>  <br></h6>
                                                </th></tr></table></th>
                                            </tr></tbody></table>
                                                <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                                  <th class="small-12 large-12 columns first last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 30px; padding-left: 30px; padding-right: 30px; text-align: left; width: 570px;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th>
                <th class="expander" style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0 !important; text-align: left; visibility: hidden; width: 0;"></th></tr></table></th>
                                                </tr></tbody></table>
                                                <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                                  <th class="footer small-16 large-16 columns first last" style="Margin: 0 auto; background-color: #ebf4f7; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 30px; padding-right: 30px; padding-top: 30px; text-align: left; width: 770px;">
                <table role="Presentation"  style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                                                    <p style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                                      Telefony kontaktowe: <b>1 9999*</b> lub +48 61 81 19999* (dzwoniąc z zagranicy)
                                                    </p>
                                                    <p class="footer-smaller" style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 11px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                                      *opłata za minutę zgodnie z taryfą danego operatora
                                                    </p>
                                                    <br>
                                                    <p class="footer-smaller" style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 11px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 30px; padding: 0; text-align: left;">
                                                      Nadawcą tej wiadomości jest Santander Bank Polska S.A.<br>
                                                      Wiadomość została wygenerowana automatycznie i wysłana za zgodą odbiorcy - prosimy na nią nie odpowiadać.<br>
                                                      Identyfikator wiadomości: 1651717356 	
                                                    </p>
                                                  </th></tr></table></th>
                                                </tr></tbody></table>                                                    </td></tr></tbody></table>
                                    </center>
                                </td>
                            </tr>
                        </table>
                        <!-- prevent Gmail on iOS font size manipulation -->
                        <div style="display:none; white-space:nowrap; font:15px courier; line-height:0;"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </div>
                    </body>
                </html>
                """;

        // when
        ExpenseData expenseData = parser.parseCardPayment(emailBody);

        // then
        assertThat(expenseData.shop()).isEqualTo("Kaufland Legnicka");
        assertThat(expenseData.date()).isEqualTo("13-08-2022");
        assertThat(expenseData.amount()).isEqualTo("22,52");
    }

    @Test
    public void shouldParseChargePayment() {
        // given
        String emailBody = """
                    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
                    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                    
                    <html xmlns="http://www.w3.org/1999/xhtml" lang="pl" xml:lang="pl" style="background: #f3f3f3 !important;">
                      <head>
                        <link rel="stylesheet" type="text/css" href="../css/app.css">
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
                        <meta name="viewport" content="width=device-width">
                        <style>@media only screen {
                  html {
                    min-height: 100%;
                    background: #f3f3f3;
                  }
                }

                @media only screen and (max-width: 830px) {
                  table.body table.container .show-for-large {
                    display: none !important;
                    width: 0;
                    mso-hide: all;
                    overflow: hidden;
                  }
                }

                @media only screen and (max-width: 830px) {
                  table.body img {
                    width: auto;
                    height: auto;
                  }

                  table.body center {
                    min-width: 0 !important;
                  }

                  table.body .container {
                    width: 100% !important;
                  }

                  table.body .columns {
                    height: auto !important;
                    -moz-box-sizing: border-box;
                    -webkit-box-sizing: border-box;
                    box-sizing: border-box;
                    padding-left: 30px !important;
                    padding-right: 30px !important;
                  }

                  table.body .columns .columns {
                    padding-left: 0 !important;
                    padding-right: 0 !important;
                  }

                  th.small-1 {
                    display: inline-block !important;
                    width: 6.25% !important;
                  }

                  th.small-2 {
                    display: inline-block !important;
                    width: 12.5% !important;
                  }

                  th.small-3 {
                    display: inline-block !important;
                    width: 18.75% !important;
                  }

                  th.small-9 {
                    display: inline-block !important;
                    width: 56.25% !important;
                  }

                  th.small-12 {
                    display: inline-block !important;
                    width: 75% !important;
                  }

                  th.small-14 {
                    display: inline-block !important;
                    width: 87.5% !important;
                  }

                  th.small-16 {
                    display: inline-block !important;
                    width: 100% !important;
                  }
                }

                @media screen {
                  p,
                  li {
                    font-family: 'Open Sans', Arial, sans-serif !important;
                  }
                }</style>
                      </head>
                      <body style="-moz-box-sizing: border-box; -ms-text-size-adjust: 100%; -webkit-box-sizing: border-box; -webkit-text-size-adjust: 100%; Margin: 0; background: #f3f3f3 !important; box-sizing: border-box; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; min-width: 100%; padding: 0; text-align: left; width: 100% !important;">
                        <span class="preheader" style="color: #f3f3f3; display: none !important; font-size: 1px; line-height: 1px; max-height: 0px; max-width: 0px; mso-hide: all !important; opacity: 0; overflow: hidden; visibility: hidden;">Santander Bank Polska S.A.</span>
                        <table class="body" style="Margin: 0; background: #f3f3f3 !important; border-collapse: collapse; border-spacing: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; height: 100%; line-height: 1.71; margin: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                          <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <td class="center" align="center" valign="top" style="-moz-hyphens: auto; -webkit-hyphens: auto; Margin: 0; border-collapse: collapse !important; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; hyphens: auto; line-height: 1.71; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word;">
                              <center data-parsed="" style="min-width: 800px; width: 100%;">
                                  <table align="center" class="container float-center" style="Margin: 0 auto; background: #ffffff; border-collapse: collapse; border-spacing: 0; float: none; margin: 0 auto; margin-left: 0 !important; margin-right: 0 !important; padding: 0; text-align: center; vertical-align: top; width: 800px;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <td style="-moz-hyphens: auto; -webkit-hyphens: auto; Margin: 0; border-collapse: collapse !important; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; hyphens: auto; line-height: 1.71; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word;">
                                    <table class="spacer" style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <td height="30pxpx" style="-moz-hyphens: auto; -webkit-hyphens: auto; Margin: 0; border-collapse: collapse !important; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 30pxpx; font-weight: normal; hyphens: auto; line-height: 30pxpx; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word;">&#xA0;</td></tr></tbody></table> 
                                    <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                      <th class="small-16 large-14 columns first last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 30px; padding-right: 30px; text-align: left; width: 670px;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                                        <h2 style="Margin: 0; Margin-bottom: 0px; color: #ec0000; font-family: Arial, sans-serif; font-size: 30px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 90px; padding: 0; text-align: left; word-wrap: normal;">
                                          <img src="https://static3.santander.pl/asset/L/o/g/Logo-Santander_86969.png" alt="Santander Bank Polska S.A." style="-ms-interpolation-mode: bicubic; clear: both; display: block; max-width: 100%; outline: none; text-decoration: none; width: auto;">
                                        </h2>
                                      </th></tr></table></th>
                                    </tr></tbody></table>
                                    <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                      <th class="small-16 large-16 columns first last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 30px; padding-right: 30px; text-align: left; width: 770px;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">    
                        <h6 style="Margin: 0; Margin-bottom: 0px; color: inherit; font-family: Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left; word-wrap: normal;">Stan Twojego konta zmniejszył się o <b>3,79 PLN</b>.</h6>
                        <hr style="border-color: #ebf4f7; border-style: solid;">
                        <br>
                        <p style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                          Szczegóły:
                        </p>
                        <br>
                          <table class="row inline-container" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                            <th class="inline-container small-3 large-2 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 12.5%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                Z konta:
                              </p>
                            </th></tr></table></th>
                            <th class="small-9 large-9 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 56.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <b>
                                63 1090 2516 0000 0001 4485 6849
                              </b>
                            </th></tr></table></th>
                          </tr></tbody></table>
                          <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                            <th class="small-3 large-2 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 12.5%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                Ile:
                              </p>
                            </th></tr></table></th>
                            <th class="small-9 large-9 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 56.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <b>
                                3,79 PLN
                              </b>
                            </th></tr></table></th>
                          </tr></tbody></table>
                          <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                            <th class="small-3 large-2 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 12.5%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                Tytuł:
                              </p>
                            </th></tr></table></th>
                            <th class="small-9 large-9 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 56.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <b>
                                Zakup BLIK SHOPEE TOWAROWA 28 ref:68246094100
                              </b>
                            </th></tr></table></th>
                          </tr></tbody></table>
                          <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                            <th class="small-3 large-2 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 12.5%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                Kiedy:
                              </p>
                            </th></tr></table></th>
                            <th class="small-9 large-9 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 56.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <b>
                                18-08-2022
                              </b>
                            </th></tr></table></th>
                          </tr></tbody></table>
                          <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th></tr></table></th>
                            <th class="small-3 large-2 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 12.5%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                Data księgowania:
                              </p>
                            </th></tr></table></th>
                            <th class="small-9 large-9 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 56.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <b>
                                18-08-2022
                              </b>
                            </th></tr></table></th>
                          </tr></tbody></table>
                          <br>
                          <p style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                            Saldo po operacji:
                          </p>
                          <br>
                          <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                            <th class="show-for-large small-0 large-1 columns first" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 6.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                        
                            </th></tr></table></th>
                            <th class="small-3 large-2 columns" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 12.5%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <p class="alternative-text" style="Margin: 0; Margin-bottom: 0px; color: #6f7779; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                Saldo:
                              </p>
                            </th></tr></table></th>
                            <th class="small-9 large-9 columns last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 0 !important; padding-right: 0 !important; text-align: left; width: 56.25%;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                              <b>
                                2 211,96 PLN
                              </b>
                            </th></tr></table></th>
                          </tr></tbody></table>
                      
                                                    </th></tr></table></th>
                                                </tr></tbody></table>
                                                    <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                                      <th class="small-12 large-12 columns first last" style="Margin: 0 auto; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 30px; padding-left: 30px; padding-right: 30px; text-align: left; width: 570px;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;"></th>
                <th class="expander" style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0 !important; text-align: left; visibility: hidden; width: 0;"></th></tr></table></th>
                                                    </tr></tbody></table>
                                                    <table class="row" style="border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%;"><tbody>
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                                                      <th class="footer small-16 large-16 columns first last" style="Margin: 0 auto; background-color: #ebf4f7; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0 auto; padding: 0; padding-bottom: 0px; padding-left: 30px; padding-right: 30px; padding-top: 30px; text-align: left; width: 770px;">
                <table style="border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%;">
                <tr style="padding: 0; text-align: left; vertical-align: top;">
                <th style="Margin: 0; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; padding: 0; text-align: left;">
                                                        <p style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 14px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                                          Telefony kontaktowe: <b>1 9999*</b> lub +48 61 81 19999* (dzwoniąc z zagranicy)
                                                        </p>
                                                        <p class="footer-smaller" style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 11px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 0px; padding: 0; text-align: left;">
                                                          *opłata za minutę zgodnie z taryfą danego operatora
                                                        </p>
                                                        <br>
                                                        <p class="footer-smaller" style="Margin: 0; Margin-bottom: 0px; color: #0a0a0a; font-family: Arial, sans-serif; font-size: 11px; font-weight: normal; line-height: 1.71; margin: 0; margin-bottom: 30px; padding: 0; text-align: left;">
                                                          Nadawcą tej wiadomości jest Santander Bank Polska S.A.<br>
                                                          Wiadomość została wygenerowana automatycznie i wysłana za zgodą odbiorcy - prosimy na nią nie odpowiadać.<br>
                                                          Identyfikator wiadomości: 1658519670 	
                                                        </p>
                                                      </th></tr></table></th>
                                                    </tr></tbody></table>                                                    </td></tr></tbody></table>
                                        </center>
                                    </td>
                                </tr>
                            </table>
                            <!-- prevent Gmail on iOS font size manipulation -->
                            <div style="display:none; white-space:nowrap; font:15px courier; line-height:0;"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </div>
                        </body>
                    </html>""";

        // when
        ExpenseData expenseData = parser.parseChargePayment(emailBody);

        // then
        assertThat(expenseData.shop()).isEqualTo("?");
        assertThat(expenseData.date()).isEqualTo("18-08-2022");
        assertThat(expenseData.amount()).isEqualTo("3,79");
        assertThat(expenseData.title()).isEqualTo("BLIK SHOPEE TOWAROWA 28 ref:68246094100");
    }
}