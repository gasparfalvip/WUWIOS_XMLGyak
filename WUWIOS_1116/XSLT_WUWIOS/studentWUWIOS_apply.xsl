<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		
		<html>
			<body>
				<h2>FM apple- template</h2>
				
				<xsl:for-each select = "class/student">
							<p>ID: <xsl:value-of select="@id"></xsl:value-of></p>
							
							<p>Vezeteknev: <span style = "color : green"><xsl:value-of select="vezeteknev"></xsl:value-of></span> </p>
							<p>Keresztnev: <span style = "color : burgundy"><xsl:value-of select="keresztnev"></xsl:value-of></span> </p>
							<p>Becenev: <span style = "color : black"><xsl:value-of select="becenev"></xsl:value-of></span> </p>
							<p>Kor: <span style = "color : blue"><xsl:value-of select="kor"></xsl:value-of></span> </p>
							<p>Osztondij: <span style = "color : red"><xsl:value-of select="osztondij"></xsl:value-of></span> </p>
					</xsl:for-each>>
				
			</body>
		</html>
		
	</xsl:template>
</xsl:stylesheet>