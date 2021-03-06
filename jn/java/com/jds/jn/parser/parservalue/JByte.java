package com.jds.jn.parser.parservalue;

import java.awt.Color;

import javax.swing.ImageIcon;

import com.jds.jn.parser.formattree.Part;
import com.jds.jn.util.ImageStatic;
import com.jds.nio.buffer.NioBuffer;

/**
 * Author: VISTALL
 * Company: J Develop Station
 * Date:  15:53:13/07.04.2010
 */
public class JByte extends ParserValue.DigitalValueParser<Byte>
{
	@Override
	public Byte getValue(NioBuffer b, Part part)
	{
		return b.get();
	}

	@Override
	public ImageIcon getIcon()
	{
		return ImageStatic.PART_BYTE;
	}

	@Override
	public Color getColor()
	{
		return Color.PINK;
	}

	@Override
	public int length()
	{
		return 1;
	}
}
