package com.jds.jn.gui.panels.viewpane;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jds.jn.gui.panels.ViewPane;
import com.jds.jn.gui.panels.viewpane.packetlist.DecPacketListPane;
import com.jds.jn.gui.panels.viewpane.packetlist.NotDecPacketListPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

/**
 * Author: VISTALL
 * Company: J Develop Station
 * Date: Sep 29, 2009
 * Time: 10:00:48 PM
 */
public class PacketList extends JPanel
{
	private JPanel root;
	public boolean IS_HIDE = false;
	protected NotDecPacketListPane _notdec_packetListPane;
	protected DecPacketListPane _Dec_packetListPane;
	protected ViewPane _pane;

	JRadioButton radio1;
	JRadioButton radio2;
	JPopupMenu popup;

	public PacketList(ViewPane pane)
	{
		_pane = pane;
		$$$setupUI$$$();
		_notdec_packetListPane = new NotDecPacketListPane(_pane);
		_Dec_packetListPane = new DecPacketListPane(_pane);

		//add(_notdec_packetListPane);

		popup = new JPopupMenu();


		radio1 = new JRadioButton(ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("DecodeList"));

		radio1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PacketList.this.remove(_notdec_packetListPane);
				add(_Dec_packetListPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
				updateUI();
			}
		});

		radio2 = new JRadioButton(ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("NotDecodeList"));
		radio2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PacketList.this.remove(_Dec_packetListPane);
				add(_notdec_packetListPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
				updateUI();
			}
		});

		registerKeyboardAction(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				popup.show(PacketList.this, 0, 0);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

		ButtonGroup group = new ButtonGroup();

		group.add(radio1);
		group.add(radio2);

		popup.add(radio1);
		popup.add(radio2);

		addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					popup.show(PacketList.this, e.getX(), e.getY());
				}
			}

			@Override
			public void mousePressed(MouseEvent e)
			{

			}

			@Override
			public void mouseReleased(MouseEvent e)
			{

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{

			}

			@Override
			public void mouseExited(MouseEvent e)
			{

			}
		});
	}

	public DecPacketListPane get_packetListPane()
	{
		return _Dec_packetListPane;//_Dec_packetListPane;
	}

	public NotDecPacketListPane getNotDecPacketListPane()
	{
		return _notdec_packetListPane;//_notdec_packetListPane;
	}

	private void createUIComponents()
	{
		root = this;
		setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
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
		createUIComponents();
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$()
	{
		return root;
	}
}