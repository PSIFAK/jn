package com.jds.jn.network.methods.jpcap.buffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Author: VISTALL
 * Company: J Develop Station
 * Date: 31/12/2009
 * Time: 21:42:26
 */
public class LittleEndianShortPacketBuffer implements IPacketBuffer
{
	private ByteBuffer _buf;

	public LittleEndianShortPacketBuffer()
	{
		_buf = ByteBuffer.allocate(65535);
		_buf.order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void putData(byte[] dat)
	{
		_buf.put(dat);
	}

	@Override
	public int nextAvaliablePacket()
	{
		if (_buf.position() < 2)
		{
			return 0;
		}

		int size = _buf.getShort(0) & 0xFFFF;

		if (size == 2)
		{
			_buf.position(0);
			return 0;
		}

		if (size > _buf.position())
		{
			return 0;
		}

		return (size - 2);
	}

	@Override
	public void getNextPacket(byte[] header, byte[] data)
	{
		_buf.limit(_buf.position());

		_buf.position(0);

		_buf.get(header);

		_buf.get(data);

		_buf.compact();
	}
}

