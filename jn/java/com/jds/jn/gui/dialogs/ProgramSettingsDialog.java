package com.jds.jn.gui.dialogs;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import com.intellij.uiDesigner.core.*;
import com.jds.jn.Jn;
import com.jds.jn.config.RValues;
import com.jds.jn.gui.listeners.ColorChooseMouseListener;

public class ProgramSettingsDialog extends JDialog
{
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTabbedPane _tabbedPane1;
	private JCheckBox _useTrayCheckBox;
	private JCheckBox _savePacketsInDecodeCheckBox;
	private JSlider _mainVisible;
	private JPanel _sampleBackground;
	private JPanel _sampleForeground;
	private JPanel _sampleForeground3;
	private JPanel _sampleForeground4;
	private JPanel _sampleBackground3;
	private JPanel _sampleBackground4;

	public ProgramSettingsDialog()
	{
		super(Jn.getForm(), "Program Settings", true);

		setContentPane(contentPane);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				onOK();
			}
		});

		buttonCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				onCancel();
			}
		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				onCancel();
			}
		});

		contentPane.registerKeyboardAction(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		_sampleBackground.addMouseListener(new ColorChooseMouseListener(this, _sampleBackground, RValues.PACKET_FORM_SELECT_BACKGROUND_COLOR, "Choose color for background"));
		_sampleForeground.addMouseListener(new ColorChooseMouseListener(this, _sampleForeground, RValues.PACKET_FORM_SELECT_FOREGROUND_COLOR, "Choose color for foreground"));

		_sampleBackground3.addMouseListener(new ColorChooseMouseListener(this, _sampleBackground3, RValues.PACKET_FORM_SELECT_BACKGROUND_COLOR_2, "Choose color for background"));
		_sampleForeground3.addMouseListener(new ColorChooseMouseListener(this, _sampleForeground3, RValues.PACKET_FORM_SELECT_FOREGROUND_COLOR_2, "Choose color for foreground"));

		_sampleBackground4.addMouseListener(new ColorChooseMouseListener(this, _sampleBackground4, RValues.PACKET_FORM_NOT_SELECT_BACKGROUND_COLOR_2, "Choose color for background"));
		_sampleForeground4.addMouseListener(new ColorChooseMouseListener(this, _sampleForeground4, RValues.PACKET_FORM_NOT_SELECT_FOREGROUND_COLOR_2, "Choose color for foreground"));

		load();

		setSize(600, 400);
	}

	public void load()
	{
		_useTrayCheckBox.setSelected(RValues.USE_TRAY.asBoolean());
		_savePacketsInDecodeCheckBox.setSelected(RValues.SAVE_AS_DECODE.asBoolean());
		_mainVisible.setValue((int) (RValues.MAIN_VISIBLE.asFloat() * 100F));
		_sampleBackground.setBackground(RValues.PACKET_FORM_SELECT_BACKGROUND_COLOR.asTColor());
		_sampleForeground.setBackground(RValues.PACKET_FORM_SELECT_FOREGROUND_COLOR.asTColor());
		_sampleBackground3.setBackground(RValues.PACKET_FORM_SELECT_BACKGROUND_COLOR_2.asTColor());
		_sampleForeground3.setBackground(RValues.PACKET_FORM_SELECT_FOREGROUND_COLOR_2.asTColor());
		_sampleBackground4.setBackground(RValues.PACKET_FORM_NOT_SELECT_BACKGROUND_COLOR_2.asTColor());
		_sampleForeground4.setBackground(RValues.PACKET_FORM_NOT_SELECT_FOREGROUND_COLOR_2.asTColor());
	}

	private void onOK()
	{
		RValues.USE_TRAY.setVal(_useTrayCheckBox.isSelected());
		RValues.SAVE_AS_DECODE.setVal(_savePacketsInDecodeCheckBox.isSelected());
		RValues.MAIN_VISIBLE.setVal(_mainVisible.getValue() / 100F);
		Jn.getForm().updateVisible();
		dispose();
	}

	private void onCancel()
	{
		dispose();
	}

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$()
	{
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
		panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		buttonOK = new JButton();
		buttonOK.setText("OK");
		panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		buttonCancel = new JButton();
		this.$$$loadButtonText$$$(buttonCancel, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("Cancel"));
		panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_tabbedPane1 = new JTabbedPane();
		_tabbedPane1.setTabPlacement(1);
		panel3.add(_tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		_tabbedPane1.addTab(ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("General"), panel4);
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel4.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel5.add(panel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel6.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_useTrayCheckBox = new JCheckBox();
		this.$$$loadButtonText$$$(_useTrayCheckBox, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("UseTray"));
		panel7.add(_useTrayCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_savePacketsInDecodeCheckBox = new JCheckBox();
		this.$$$loadButtonText$$$(_savePacketsInDecodeCheckBox, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("SavePacketsInDecode"));
		panel7.add(_savePacketsInDecodeCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer2 = new Spacer();
		panel5.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		_tabbedPane1.addTab(ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("GUISettings"), panel8);
		final JPanel panel9 = new JPanel();
		panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel8.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel9.setBorder(BorderFactory.createTitledBorder(ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("Visible")));
		_mainVisible = new JSlider();
		_mainVisible.setPaintLabels(false);
		_mainVisible.setPaintTicks(true);
		_mainVisible.setSnapToTicks(false);
		_mainVisible.setValueIsAdjusting(false);
		panel9.add(_mainVisible, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		panel8.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JPanel panel10 = new JPanel();
		panel10.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		_tabbedPane1.addTab("Packet Form", panel10);
		final JPanel panel11 = new JPanel();
		panel11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel10.add(panel11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel12 = new JPanel();
		panel12.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel11.add(panel12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel13 = new JPanel();
		panel13.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel12.add(panel13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel13.setBorder(BorderFactory.createTitledBorder("Colors"));
		final JPanel panel14 = new JPanel();
		panel14.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel13.add(panel14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel14.setBorder(BorderFactory.createTitledBorder("Selection Hex Dump"));
		final JPanel panel15 = new JPanel();
		panel15.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel14.add(panel15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Background:");
		panel15.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
		_sampleBackground = new JPanel();
		_sampleBackground.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel15.add(_sampleBackground, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, 25), new Dimension(25, 25), 0, false));
		_sampleBackground.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
		final JPanel panel16 = new JPanel();
		panel16.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel14.add(panel16, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_sampleForeground = new JPanel();
		_sampleForeground.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel16.add(_sampleForeground, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, 25), new Dimension(25, 25), 0, false));
		_sampleForeground.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
		final JLabel label2 = new JLabel();
		label2.setText("Foreground:");
		panel16.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
		final JPanel panel17 = new JPanel();
		panel17.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel13.add(panel17, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel17.setBorder(BorderFactory.createTitledBorder("Selection Part"));
		final JPanel panel18 = new JPanel();
		panel18.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel17.add(panel18, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Background:");
		panel18.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
		_sampleBackground3 = new JPanel();
		_sampleBackground3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel18.add(_sampleBackground3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, 25), new Dimension(25, 25), 0, false));
		_sampleBackground3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
		final JPanel panel19 = new JPanel();
		panel19.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel17.add(panel19, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_sampleForeground3 = new JPanel();
		_sampleForeground3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel19.add(_sampleForeground3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, 25), new Dimension(25, 25), 0, false));
		_sampleForeground3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
		final JLabel label4 = new JLabel();
		label4.setText("Foreground:");
		panel19.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
		final JPanel panel20 = new JPanel();
		panel20.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel13.add(panel20, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel20.setBorder(BorderFactory.createTitledBorder("Not Selection Part"));
		final JPanel panel21 = new JPanel();
		panel21.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel20.add(panel21, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Background:");
		panel21.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
		_sampleBackground4 = new JPanel();
		_sampleBackground4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel21.add(_sampleBackground4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, 25), new Dimension(25, 25), 0, false));
		_sampleBackground4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
		final JPanel panel22 = new JPanel();
		panel22.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel20.add(panel22, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_sampleForeground4 = new JPanel();
		_sampleForeground4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel22.add(_sampleForeground4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, 25), new Dimension(25, 25), 0, false));
		_sampleForeground4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
		final JLabel label6 = new JLabel();
		label6.setText("Foreground:");
		panel22.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
	}

	/**
	 * @noinspection ALL
	 */
	private void $$$loadButtonText$$$(AbstractButton component, String text)
	{
		StringBuffer result = new StringBuffer();
		boolean haveMnemonic = false;
		char mnemonic = '\0';
		int mnemonicIndex = -1;
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == '&')
			{
				i++;
				if(i == text.length())
				{
					break;
				}
				if(!haveMnemonic && text.charAt(i) != '&')
				{
					haveMnemonic = true;
					mnemonic = text.charAt(i);
					mnemonicIndex = result.length();
				}
			}
			result.append(text.charAt(i));
		}
		component.setText(result.toString());
		if(haveMnemonic)
		{
			component.setMnemonic(mnemonic);
			component.setDisplayedMnemonicIndex(mnemonicIndex);
		}
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$()
	{
		return contentPane;
	}
}
