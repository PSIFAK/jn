package com.jds.jn.gui.panels;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import com.intellij.uiDesigner.core.*;
import com.jds.jn.data.xml.holder.ProtocolHolder;
import com.jds.jn.network.listener.types.ListenerType;
import com.jds.jn.network.profiles.NetworkProfile;
import com.jds.jn.network.profiles.NetworkProfilePart;
import com.jds.jn.protocol.Protocol;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

/**
 * Author: VISTALL
 * Company: J Develop Station
 * Date: 03/01/2010
 * Time: 18:49:49
 */
public class NetworkSettingPane extends JPanel
{
	private JPanel root;
	private JTabbedPane _tabbedPane1;
	private JComboBox _protocols;
	private JTextField _remoteHost;
	private JTextField _localHost;
	private JTextField _localPort;
	private JTextField _remotePort;
	private JComboBox _deviceBox;
	private JTextField _prortDevice;
	private JPanel _mPane;
	private JLabel _desc;
	private JTextArea _serverList;
	private JTextField _packetId;
	private JTextField _serverId;
	private JButton _defaultBtn;
	private JPanel _mainPane;

	private final NetworkProfilePart _type;

	public NetworkSettingPane(NetworkProfilePart type)
	{
		_type = type;
		$$$setupUI$$$();

		_desc.setText("");
		NetworkInterface[] alldevs = JpcapCaptor.getDeviceList();

		_deviceBox.removeAllItems();

		for(NetworkInterface dev : alldevs)
		{
			DevInfo d = new DevInfo(dev);
			_deviceBox.addItem(d);
		}

		_deviceBox.setSelectedItem(null);

		_deviceBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				DevInfo dev = (DevInfo) _deviceBox.getSelectedItem();
				if(dev == null)
				{
					_desc.setText("");
				}
				else
				{
					_desc.setText(dev._device.description);
				}
			}
		});

		_defaultBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				serverListToDefault();
			}
		});

		_mainPane.setVisible(_type.getType() == ListenerType.Auth_Server);

		load();
	}

	public void serverListToDefault()
	{
		_serverList.setText("writeC(%PACKET_ID%);" + "\nwriteC(0x01);" + "\nwriteC(%SERVER_ID%);" + "\nwriteC(%SERVER_ID%);" + "\nwriteIP(%LOCAL_HOST%);" + "\nwriteD(%LOCAL_PORT%);" + "\nwriteC(0);" + "\nwriteC(1);" + "\nwriteH(0);" + "\nwriteH(5000);" + "\nwriteC(1);" + "\nwriteD(1);" + "\nwriteC(1);");
	}

	public void load()
	{
		_remoteHost.setText(_type.getRemoteHost());
		_localHost.setText(_type.getLocalHost());
		_localPort.setText(String.valueOf(_type.getLocalPort()));
		_remotePort.setText(String.valueOf(_type.getRemotePort()));
		_deviceBox.setSelectedIndex(_type.getDeviceId());
		_packetId.setText(String.valueOf(_type.getPacketId()));
		_serverId.setText(String.valueOf(_type.getServerId()));
		if(_type.getServerList() != null)
		{
			_serverList.setText(_type.getServerList());
		}
		else
		{
			serverListToDefault();
		}

		_prortDevice.setText(String.valueOf(_type.getDevicePort()));

		_protocols.removeAllItems();

		for(Protocol rpof : ProtocolHolder.getInstance().getProtocols())
		{
			_protocols.addItem(rpof);
		}

		if(_type.getProtocol() != null && !_type.getProtocol().equals(""))
		{
			Protocol prot = ProtocolHolder.getInstance().getProtocolByName(_type.getProtocol());

			_protocols.setSelectedItem(prot);
		}
	}

	public String generateServerList(String text)
	{
		text = text.replace("%LOCAL_HOST%", _localHost.getText());
		text = text.replace("%LOCAL_PORT%", _localPort.getText());
		text = text.replace("%SERVER_ID%", _serverId.getText());
		text = text.replace("%PACKET_ID%", _packetId.getText());
		return text;
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
		root.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		_mPane = new JPanel();
		_mPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		root.add(_mPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		_mPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Protocol:");
		panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_protocols = new JComboBox();
		panel1.add(_protocols, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_tabbedPane1 = new JTabbedPane();
		_mPane.add(_tabbedPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 5, 5), -1, -1));
		_tabbedPane1.addTab("Proxy", panel2);
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Local Host:");
		panel4.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_localHost = new JTextField();
		_localHost.setText("127.0.0.1");
		panel4.add(_localHost, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Remote Host:");
		panel5.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_remoteHost = new JTextField();
		_remoteHost.setText("127.0.0.1");
		panel5.add(_remoteHost, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("Local Port:");
		panel6.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_localPort = new JTextField();
		_localPort.setText("2106");
		panel6.add(_localPort, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Remote Port:");
		panel7.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_remotePort = new JTextField();
		_remotePort.setText("2107");
		panel7.add(_remotePort, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel2.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		_mainPane = new JPanel();
		_mainPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		_mainPane.setVisible(true);
		panel2.add(_mainPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_mainPane.setBorder(BorderFactory.createTitledBorder(ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("ServerListPacketStructure")));
		_serverList = new JTextArea();
		_mainPane.add(_serverList, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
		_mainPane.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		this.$$$loadLabelText$$$(label6, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("PacketId"));
		panel8.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_packetId = new JTextField();
		_packetId.setText("0x04");
		panel8.add(_packetId, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		_serverId = new JTextField();
		_serverId.setText("0x01");
		panel8.add(_serverId, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JLabel label7 = new JLabel();
		this.$$$loadLabelText$$$(label7, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("ServerID"));
		panel8.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_defaultBtn = new JButton();
		this.$$$loadButtonText$$$(_defaultBtn, ResourceBundle.getBundle("com/jds/jn/resources/bundle/LanguageBundle").getString("Default"));
		panel8.add(_defaultBtn, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel9 = new JPanel();
		panel9.setLayout(new GridLayoutManager(4, 1, new Insets(5, 5, 5, 5), -1, -1));
		_tabbedPane1.addTab("Jpcap", panel9);
		final JPanel panel10 = new JPanel();
		panel10.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel9.add(panel10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label8 = new JLabel();
		label8.setText("Device:");
		panel10.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_deviceBox = new JComboBox();
		panel10.add(_deviceBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel11 = new JPanel();
		panel11.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
		panel9.add(panel11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label9 = new JLabel();
		label9.setText("Port:");
		panel11.add(label9, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		_prortDevice = new JTextField();
		panel11.add(_prortDevice, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final Spacer spacer2 = new Spacer();
		panel11.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		panel11.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer4 = new Spacer();
		panel9.add(spacer4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JPanel panel12 = new JPanel();
		panel12.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		panel9.add(panel12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_desc = new JLabel();
		_desc.setText("Description");
		panel12.add(_desc, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer5 = new Spacer();
		panel12.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	private void $$$loadLabelText$$$(JLabel component, String text)
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
			component.setDisplayedMnemonic(mnemonic);
			component.setDisplayedMnemonicIndex(mnemonicIndex);
		}
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
		return root;
	}

	private class DevInfo
	{
		private final NetworkInterface _device;

		DevInfo(NetworkInterface d)
		{
			_device = d;
		}

		@Override
		public String toString()
		{
			return _device.name;
		}
	}

	public ListenerType type()
	{
		return _type.getType();
	}

	public void set(ListenerType type, NetworkProfile prof)
	{
		NetworkProfilePart part = prof.getPart(type);
		part.setLocalHost(_localHost.getText());
		part.setRemoteHost(_remoteHost.getText());
		DevInfo info = (DevInfo) _deviceBox.getSelectedItem();

		part.setDeviceId(_deviceBox.getSelectedIndex());

		part.setDevicePort(Integer.parseInt(_prortDevice.getText()));
		part.setLocalPort(Integer.parseInt(_localPort.getText()));
		part.setRemotePort(Integer.parseInt(_remotePort.getText()));
		part.setServerId(Integer.parseInt(_serverId.getText()));
		part.setPacketId(Integer.parseInt(_packetId.getText()));
		part.setServerList(generateServerList(_serverList.getText()));

		if(_protocols.getSelectedItem() == null)
		{
			part.setProtocol(null);
		}
		else
		{
			part.setProtocol(((Protocol) _protocols.getSelectedItem()).getName());
		}
	}

	private void createUIComponents()
	{
		root = this;
	}

}
