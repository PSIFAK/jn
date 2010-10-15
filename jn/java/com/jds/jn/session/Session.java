package com.jds.jn.session;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jds.jn.classes.CLoader;
import com.jds.jn.crypt.ProtocolCrypter;
import com.jds.jn.gui.panels.ViewPane;
import com.jds.jn.gui2.MainForm.ribbon.SessionMenu.SessionRibbonTaskGroup;
import com.jds.jn.network.listener.types.ListenerType;
import com.jds.jn.network.methods.IMethod;
import com.jds.jn.network.packets.CryptedPacket;
import com.jds.jn.network.packets.DecryptedPacket;
import com.jds.jn.parser.packetfactory.IPacketListener;
import com.jds.jn.parser.packetfactory.lineage2.L2World;
import com.jds.jn.protocol.Protocol;
import com.jds.jn.protocol.ProtocolManager;
import com.jds.jn.util.ThreadPoolManager;
import com.jds.jn.util.version_control.Version;

/**
 * @author Ulysses R. Ribeiro
 * @author Gilles Duboscq
 * @author VISTALL
 */
public class Session
{
	private static final Logger _log = Logger.getLogger(Session.class);

	private long _sessionId;
	private Version _version = Version.CURRENT;

	private ProtocolCrypter _crypt;
	private Protocol _protocol;

	private final List<CryptedPacket> _notDecryptPackets = new ArrayList<CryptedPacket>();
	private final List<DecryptedPacket> _decryptPackets = new ArrayList<DecryptedPacket>();

	private final IMethod _method;
	private final ListenerType _type;

	// gui
	private final ViewPane _viewPane = new ViewPane(this);
	private final SessionRibbonTaskGroup _ribbonGroup = new SessionRibbonTaskGroup(this);

	private final List<IPacketListener> _invokes = new ArrayList<IPacketListener>();

	public Session(IMethod iMethod, long sessionId)
	{
		this(iMethod, iMethod.getListenerType(), sessionId);
	}

	public Session(ListenerType type, long sessionId)
	{
		this(null, type, sessionId);
	}

	private Session(IMethod method, ListenerType type, long sessionId)
	{
		_method = method;
		_type = type;
		_sessionId = sessionId;

		Protocol protocol = ProtocolManager.getInstance().getProtocol(type);
		if(protocol == null)
		{
			_log.info("Not find protocol for type: " + type);
			throw new IllegalArgumentException("Not find protocol");
		}

		_protocol = protocol;

		init(true);
	}

	private void init(boolean crypted)
	{    		

		if (crypted)
		{
			try
			{
				Class<?> clazz = CLoader.getInstance().forName("crypt." + getProtocol().getEncryption() + "Crypter");
				_crypt = (ProtocolCrypter) clazz.newInstance();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				Class<?> clazz = CLoader.getInstance().forName("crypt.NullCrypter");
				_crypt = (ProtocolCrypter) clazz.newInstance();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		_crypt.setProtocol(getProtocol());

		_invokes.add(L2World.getInstance());
		//List<Class<? extends IPacketListener>> l = new ArrayList<Class<? extends IPacketListener>>();
		//l.add(L2World.class);

		/*for(Class<? extends IPacketListener> cl : l)
		{
			try
			{
				_invokes.add(cl.newInstance());
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		} */
	}

	public DecryptedPacket decode(CryptedPacket packet)
	{
		byte data[] = Arrays.copyOf(packet.getBuffer().array(), packet.getBuffer().array().length);

		data = _crypt.decrypt(data, packet.getPacketType());

		return new DecryptedPacket(data, packet.getPacketType(), getProtocol());
	}

	public long getSessionId()
	{
		return _sessionId;
	}

	public List<CryptedPacket> getCryptedPackets()
	{
		return _notDecryptPackets;
	}

	public Protocol getProtocol()
	{
		return _protocol;
	}

	public ProtocolCrypter getCrypt()
	{
		return _crypt;
	}

	public synchronized void receivePacket(CryptedPacket p)
	{
		 _notDecryptPackets.add(p);

		_viewPane.getCryptPacketTableModel().addRow(p);
		_viewPane.updateInfo(this);
	}

	public synchronized void receivePacket(DecryptedPacket p)
	{
		addDecryptPacket(p);

		_viewPane.getDecryptPacketTableModel().addRow(p);
		_viewPane.updateInfo(this);
	}

	public synchronized void receiveQuitPacket(CryptedPacket p)
	{
		_notDecryptPackets.add(p);

		_viewPane.getCryptPacketTableModel().addRow(p);
	}

	public synchronized void receiveQuitPacket(DecryptedPacket p)
	{
		addDecryptPacket(p);

		_viewPane.getDecryptPacketTableModel().addRow(p);
	}

	public void addDecryptPacket(DecryptedPacket packet)
	{
		_decryptPackets.add(packet);

		fireInvokePacket(packet);
	}

	public void onShow()
	{
		_viewPane.drawThis();

		_viewPane.updateInfo(this);
		_viewPane.getPacketListPane().getPacketTable().updateUI();
		_viewPane.getNotDecPacketListPane().getPacketTable().updateUI();
	}

	public void close()
	{
		fireClose();
		
		SessionTable.getInstance().removeGameSession(getSessionId());
	}

	public IMethod getMethod()
	{
		return _method;
	}

	public ListenerType getListenerType()
	{
		return _type;
	}

	public ViewPane getViewPane()
	{
		return _viewPane;
	}

	public List<DecryptedPacket> getDecryptPackets()
	{
		return _decryptPackets;
	}

	public void fireInvokePacket(final DecryptedPacket o)
	{
		ThreadPoolManager.getInstance().execute(new Runnable()
		{
			@Override
			public void run()
			{
				for(IPacketListener f :_invokes)
				{
					f.invoke(o);
				}
			}
		});
	}

	public void fireClose()
	{
		/*ThreadPoolManager.getInstance().execute(new Runnable()
		{
			@Override
			public void run()
			{
				for(IPacketListener f :_invokes)
				{
					f.close();
				}
			}
		});  */
	}

	public Version getVersion()
	{
		return _version;
	}

	public void setVersion(Version version)
	{
		_version = version;
	}

	public SessionRibbonTaskGroup getRibbonGroup()
	{
		return _ribbonGroup;
	}
}

