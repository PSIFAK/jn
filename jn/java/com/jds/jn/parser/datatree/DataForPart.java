package com.jds.jn.parser.datatree;

import com.jds.jn.parser.formattree.ForPart;

import java.util.ArrayList;
import java.util.List;


public class DataForPart extends DataTreeNodeContainer
{
	private List<DataForBlock> _blockList; //list used for type casting

	public DataForPart(DataTreeNodeContainer container, ForPart part)
	{
		super(container, part);
	}

	@Override
	public void addNode(DataTreeNode node)
	{
		if (node instanceof DataForBlock)
		{
			super.addNode(node);
		}
		else
		{
			throw new IllegalArgumentException("Only DataForBlocks can be added to DataForParts");
		}
		_blockList = null; //invalidate casting list
	}

	@Override
	public List<DataForBlock> getNodes()
	{
		if (_blockList == null)
		{
			// right this is quite slow sompared to just not overriding, plus it kinda duplicates the list (even if it doesnt duplicate the content)
			// but this is the only way i found to make a clean API : getNodes() in DataForPart must have the List<DataForBlock> return type
			// just casting super.getNodes() to List<DataForBlock> will give an unsafe cast warning.
			_blockList = new ArrayList<DataForBlock>();
			for (DataTreeNode node : super.getNodes())
			{
				_blockList.add((DataForBlock) node);
			}
		}
		return _blockList;
	}

	@Override
	public String toString()
	{
		return ((ForPart) this.getModelPart()).treeString();
	}
}