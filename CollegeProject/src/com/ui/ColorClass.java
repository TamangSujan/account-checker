package com.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ColorClass {
	public Color dodgerBlue = Color.getHSBColor((210/360f), 0.88f, 1.0f);
	public Color defaultBlack = Color.getHSBColor(0.1f, 0.25f, 0.15f);
	public Color lightBlack = Color.getHSBColor(0.1f, 0.25f, 0.20f);
	public Color focusBlack = Color.getHSBColor(125/360f, 0.29f, 1.00f);
	//private Border none = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white);
	public Border tdBorderW = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.white);
	public Border tdBorderB = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black);
	public Border borderW = BorderFactory.createLineBorder(Color.white);
	public Border borderB = BorderFactory.createLineBorder(Color.black);
	//private Border dodgerBlueBorder = BorderFactory.createLineBorder(dodgerBlue);
	//private Border whiteBorder = BorderFactory.createLineBorder(Color.white);
	public Color overlayBlack = Color.getHSBColor(0.1f, 0.25f, 0.40f);
}
