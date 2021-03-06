package com.jds.jn.gui.panels.viewpane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jds.jn.gui.panels.ViewPane;
import com.jds.jn.network.listener.types.ListenerType;
import com.jds.jn.network.profiles.NetworkProfile;
import com.jds.jn.network.profiles.NetworkProfilePart;
import com.jds.jn.network.profiles.NetworkProfiles;
import com.jds.jn.protocol.Protocol;
import com.jds.jn.protocol.protocoltree.PacketFamilly;
import com.jds.jn.protocol.protocoltree.PacketInfo;

/**
 * Author: VISTALL
 * Company: J Develop Station
 * Date: 21.09.2009
 * Time: 16:48:51
 */
public class FilterPane extends HiddenPanel
{
	private JPanel main;
	private JList packetList;
	private JCheckBox filtredCheckBox;
	private JCheckBox _filterAll;

	private ViewPane _pane;
	private List<PacketInfo> _formats = new ArrayList<PacketInfo>();

	public FilterPane(ViewPane pa)
	{
		_pane = pa;
		$$$setupUI$$$();

		packetList.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				NetworkProfile profile = NetworkProfiles.getInstance().active();
				PacketInfo v = (PacketInfo) ((JList) e.getSource()).getSelectedValue();

				if(_pane.getSession() == null || profile == null || v == null)
				{
					return;
				}
				filtredCheckBox.setEnabled(true);

				ListenerType type = _pane.getSession().getListenerType();
				NetworkProfilePart part = profile.getPart(type);

				filtredCheckBox.setSelected(part.isFiltredOpcode(v.getOpcodeStr()));
			}
		});

		filtredCheckBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				NetworkProfile profile = NetworkProfiles.getInstance().active();
				PacketInfo v = (PacketInfo) packetList.getSelectedValue();
				if(_pane.getSession() == null || profile == null || v == null)
				{
					return;
				}

				ListenerType type = _pane.getSession().getListenerType();
				NetworkProfilePart part = profile.getPart(type);

				if(part.isFiltredOpcode(v.getOpcodeStr()))
				{
					part.removeFilterOpcode(v.getOpcodeStr());
				}
				else
				{
					part.addFilterOpcode(v.getOpcodeStr());
				}
			}
		});

		_filterAll.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				NetworkProfile profile = NetworkProfiles.getInstance().active();
				if(_pane.getSession() == null || profile == null)
				{
					return;
				}

				ListenerType type = _pane.getSession().getListenerType();
				NetworkProfilePart part = profile.getPart(type);

				for(PacketInfo f : _formats)
				{
					if(_filterAll.isSelected())
					{
						part.addFilterOpcode(f.getOpcodeStr());
					}
					else
					{
						part.removeFilterOpcode(f.getOpcodeStr());
					}
				}
				filtredCheckBox.setSelected(_filterAll.isSelected());
			}
		});
	}

	private void createUIComponents()
	{
		main = this;
		setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
	}

	public void drawThis()
	{
		if(_pane == null)
		{
			return;
		}

		_formats.clear();

		if(_pane.getSession() == null)
		{
			return;
		}

		Protocol currentProto = _pane.getSession().getProtocol();
		//
		getAllFormatsName(currentProto);

		Object[] list = _formats.toArray();

		packetList.setListData(list);
	}

	private void getAllFormatsName(Protocol p)
	{
		for(PacketFamilly a : p.getFamilies())
		{
			_formats.addAll(a.getFormats().values());
		}
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
		main.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		main.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		packetList = new JList();
		scrollPane1.setViewportView(packetList);
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
		main.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		filtredCheckBox = new JCheckBox();
		filtredCheckBox.setEnabled(false);
		this.$$$loadButtonText$$$(filtredCheckBox, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("IsFilter"));
		panel2.add(filtredCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel2.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		_filterAll = new JCheckBox();
		_filterAll.setText("Filter All");
		panel2.add(_filterAll, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
		return main;
	}

}
