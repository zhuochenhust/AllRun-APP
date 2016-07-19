package com.tarena.allrun.entity;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.jivesoftware.smack.packet.Message;

public class GroupChatEntity {

	// 保存当前用户在所有room中的聊天内容
	// ArrayList<message>
	// ConcurrentHashMap,Vector是安全的
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
