<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Kyivstar tariffs</h1>
                <table border="1">
                    <tr>
                        <th>TariffName</th>
                        <th>Payroll</th>
                        <th>Insidenetwork</th>
                        <th>Outsidenetwork</th>
                        <th>ToFixedPhones</th>
                        <th>SMSPrice</th>
                        <th>FavouriteNumbers</th>
                        <th>Tariffing</th>
                        <th>ConnectPayment</th>
                    </tr>
                    <xsl:for-each select="operator/tariff">
                        <xsl:sort select="payroll" data-type="number" order="descending"/>
                        <tr>
                            <td>
                                <xsl:value-of select="tariffname" />
                            </td>
                            <td>
                                <xsl:value-of select="payroll" />
                            </td>
                            <td>
                                <xsl:value-of select="callprices/insidenetwork" />
                            </td>
                            <td>
                                <xsl:value-of select="callprices/outsidenetwork" />
                            </td>
                            <td>
                                <xsl:value-of select="callprices/tofixedphones" />
                            </td>
                            <td>
                                <xsl:value-of select="smsprice" />
                            </td>  
                            <td>
                                <xsl:value-of select="parameters/favouritenumbers" />
                            </td>
                            <td>
                                <xsl:value-of select="parameters/tariffing" />
                            </td>
                            <td>
                                <xsl:value-of select="parameters/connectpayment" />
                            </td>
                        </tr>
                    </xsl:for-each>
                 </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>