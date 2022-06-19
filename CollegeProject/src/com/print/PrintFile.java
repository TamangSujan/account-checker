package com.print;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintFile {

	public PrintFile() {
	}
	public void printRecord(JPanel panel) {
		JFileChooser chooser = new JFileChooser();
		try {
			Document document = new Document();
			
			String location = chooser.getCurrentDirectory().toString();
			if(location!=null) {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(location+"/temp.pdf"));
				document.open();
				PdfContentByte contentByte = writer.getDirectContent();
				PdfTemplate template = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
				@SuppressWarnings("deprecation")
				Graphics2D g2 = template.createGraphics(panel.getWidth(), panel.getHeight());
				panel.print(g2);
				g2.dispose();
				contentByte.addTemplate(template, 5,60);
				document.close();
				writer.close();
				File file = new File(location+"/temp.pdf");
				Desktop desktop = Desktop.getDesktop();
				if(file.exists()) {
					desktop.open(file);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
