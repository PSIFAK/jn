package packet_readers.lineage2;

import java.util.Collection;

import org.napile.primitive.maps.IntObjectMap;
import org.napile.primitive.maps.impl.TreeIntObjectMap;
import packet_readers.lineage2.holders.ItemNameHolder;
import packet_readers.lineage2.holders.NpcNameHolder;
import packet_readers.lineage2.holders.SkillNameHolder;
import packet_readers.lineage2.infos.L2NpcInfo;
import packet_readers.lineage2.infos.L2Object;
import packet_readers.lineage2.infos.L2SkillInfo;

/**
 * @author VISTALL
 * @date  20:29:53/31.07.2010
 */
public class L2World
{
	public static final String OBJECT_ID = "object-id";

	// npcs
	private IntObjectMap<L2Object> _objects = new TreeIntObjectMap<L2Object>();
	private IntObjectMap<L2NpcInfo> _npcInfosByNpcId = new TreeIntObjectMap<L2NpcInfo>();
	private IntObjectMap<L2SkillInfo> _skills = new TreeIntObjectMap<L2SkillInfo>();

	//values
	private int _userLevel;
	private int _userClassId = -1;

	public L2World()
	{
		ItemNameHolder.getInstance();
		NpcNameHolder.getInstance();
		SkillNameHolder.getInstance();
	}

	//===========================================================================================
	// 				Npcs
	//===========================================================================================
	public void addObject(int obj, L2Object npc)
	{
		_objects.put(obj, npc);
	}

	public void addNpcByNpcId(int npcId, L2NpcInfo npc)
	{
		_npcInfosByNpcId.put(npcId, npc);
	}

	public L2Object getObject(int obj)
	{
		return _objects.get(obj);
	}

	public L2NpcInfo getNpcByNpcId(int obj)
	{
		return _npcInfosByNpcId.get(obj);
	}

	public Collection<L2NpcInfo> valuesNpc()
	{
		return _npcInfosByNpcId.values();
	}

	public IntObjectMap<L2SkillInfo> getSkills()
	{
		return _skills;
	}

	public int getUserLevel()
	{
		return _userLevel;
	}

	public void setUserLevel(int userLevel)
	{
		_userLevel = userLevel;
	}

	public int getUserClassId()
	{
		return _userClassId;
	}

	public void setUserClassId(int userClassId)
	{
		_userClassId = userClassId;
	}
}
