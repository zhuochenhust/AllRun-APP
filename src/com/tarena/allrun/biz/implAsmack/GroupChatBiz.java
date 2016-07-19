package com.tarena.allrun.biz.implAsmack;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.tarena.allrun.TApplication;
import com.tarena.allrun.biz.IGroupChatBiz;
import com.tarena.allrun.entity.GroupChatEntity;
import com.tarena.allrun.util.Const;
import com.tarena.allrun.util.Tools;
import com.tarena.allrun.view.ChatActivity;

import android.app.Activity;
import android.content.Intent;

public class GroupChatBiz implements IGroupChatBiz {
	public static void sendMessage(final String body) {
		new Thread() {
			public void run() {
				try {
					Message message = new Message();
					String room = TApplication.multiUserChat.getRoom();
					message.setTo(room);
					String from = TApplication.currentUser.getUser();
					message.setFrom(from);

					message.setBody(body);
					message.setType(Type.groupchat);
					// 把信息保存到实体类
					GroupChatEntity.addMessage(room, message);
					// 发广播
					Intent intent = new Intent(Const.ACTION_SHOW_GROUP_CHAT_MESSAGE);

					TApplication.instance.sendBroadcast(intent);
					// 要对发到网上的信息进行加密
					Message encryptMessage = new Message();
					encryptMessage.setTo(room);
					encryptMessage.setFrom(from);
					byte[] data = body.getBytes();
					for (int i = 0; i < data.length; i++) {
						data[i] = Tools.encrypt(data[i]);
					}
					// encryptMessage.setBody(new String(data));
					// encryptMessage.setType(Type.groupchat);
					// TApplication.multiUserChat.sendMessage(encryptMessage);

					// 要对发到网上的信息进行解密
					Message decryptMessage = new Message();
					decryptMessage.setTo(room);
					decryptMessage.setFrom(from);
					byte[] data1 = body.getBytes();
					for (int i = 0; i < data1.length; i++) {
						data[i] = Tools.decrypt(data[i]);
					}
					decryptMessage.setBody(new String(data));
					decryptMessage.setType(Type.groupchat);
					TApplication.multiUserChat.sendMessage(decryptMessage);

				} catch (Exception e) {
					// TODO: handle exception
				}
			};
		}.start();
	}

	/**
	 * 
	 * @param activity
	 * @param roonName
	 *            allRun
	 * @param name
	 */
	public void join(final Activity activity, final String roonName, final String name) {
		new Thread() {
			public void run() {
				try {
					String room = roonName + "@conference." + TApplication.serviceName;
					MultiUserChat multiUserChat = new MultiUserChat(TApplication.xmppConnection, room);
					multiUserChat.join(name);
					TApplication.currentUser.setName(name);
					// 在别的地方会用到，作成全局变量
					TApplication.multiUserChat = multiUserChat;

					// 加入成功
					activity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							try {
								// activity.handleTextView()
								activity.startActivity(new Intent(activity, ChatActivity.class));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
					});
				} catch (Exception e) {
					activity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Tools.showInfo(activity, "加入失败");
						}
					});
				} finally {

				}

			};
		}.start();
	}

}
