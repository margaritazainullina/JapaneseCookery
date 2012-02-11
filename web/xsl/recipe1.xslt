<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" omit-xml-declaration="yes" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="result">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="info">
        <h1><xsl:value-of select="."/></h1>
    </xsl:template>    

</xsl:stylesheet>