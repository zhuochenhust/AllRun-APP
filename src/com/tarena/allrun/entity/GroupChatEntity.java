package com.tarena.allrun.entity;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.jivesoftware.smack.packet.Message;

public class GroupChatEntity {

	// ���浱ǰ�û�������room�е���������
	// ArrayList<message>
	// ConcurrentHashMap,Vector�ǰ�ȫ��
	public static ConcurrentHashMap<String, Vector<Message>> map = new ConcurrentHashMap<String, Vector<Message>>();

	public static void addMessage(String room, Message message) {
		Vector<Message> vector = map.get(room);
		if (vector == null) {
			vector = new Vector<Message>();
		}
		vector.add(message);
		map.put(room, vector);
	}
}
