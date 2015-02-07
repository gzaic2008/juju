
package com.zdnst.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;




public class EmailServer extends TimerTask {
	
	private static Logger logger = Logger.getLogger(EmailServer.class);
	/**
	 * 邮件服务器
	 */
	private String mailHost;
	/**
	 * 邮件服务器端口
	 */
	private int mailHostPort = 25;
	/**
	 * 邮件服务器验证用户
	 */
	private String user;
	/**
	 * 邮件服务器验证密码
	 */
	private String password;
	/**
	 * 通过SSL访问邮件服务器
	 */

	private String sender;

	private String max;

	private static Logger log = LogManager.getLogger(EmailServer.class);

	/**
	 * 构造函数
	 * 
	 * @throws Exception
	 *             应用异常
	 */
	public EmailServer() {

		// 邮件服务器
		// mailHost = SystemParameter.getParameterValue("email.smtp.host");
		// // 邮件服务器端口
		// try {
		// mailHostPort = Integer.parseInt(SystemParameter
		// .getParameterValueDefault("email.smtp.port", "25"));
		// } catch (NumberFormatException e) {
		// // TODO Auto-generated catch block
		// mailHostPort = 25;
		// }
		//
		// // 邮件服务器验证用户
		// user = SystemParameter.getParameterValue("email.smtp.user");
		// // 邮件服务器验证密码
		// password = SystemParameter.getParameterValue("email.smtp.password");
		//
		// // 每次发送份数
		// max = SystemParameter.getParameterValueDefault("email.send.maxper",
		// "50");
		// sender =
		// SystemParameter.getParameterValueDefault("email.smtp.sender",
		// "aaaa@coscogz.com.cn");

	}

	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            邮件标题
	 * @param content
	 *            邮件正文
	 * @param from
	 *            发件人
	 * @param to
	 *            收件人
	 * @param cc
	 *            抄送
	 * @throws Exception
	 *             应用异常
	 */
	public void sendMail(String subject, String content, String to,
			String from, String cc, String attachId, String sendNickName)
			throws Exception {
//		FileOutputStream fos = null;
//		try {
//			Properties props = System.getProperties();
//
//			props.put("mail.smtp.host", mailHost); // 邮件服务器
//			props.put("mail.smtp.port", Integer.toString(mailHostPort)); // 邮件服务器端口
//
//			props.put("mail.smtp.auth", "false"); // 指定是否需要SMTP验证
//			props.put("mail.store.protocol", "pop3");
//			props.put("mail.store.protocol", "pop3");
//			props.put("mail.transport.protocol", "smtp");
//			Session session = null;
//
////			if (SystemParameter.getParameterValueDefault(
////					"email.smtp.authrozer", "N").equals("Y")) {
////				session = Session.getDefaultInstance(props,
////						new Authenticator() {
////							protected PasswordAuthentication getPasswordAuthentication() {
////								return new PasswordAuthentication(user,
////										password);
////							}
////						});
////			} else {
////				session = Session.getDefaultInstance(props);
////			}
//
//			session.setDebug(true); // 输出debug信息
//
//			if (log.isInfoEnabled()) {
//				log.info(" to mail: " + to);
//			}
//
//			MimeMessage msg = new MimeMessage(session);
//			// remove by timpeng 2005-11-17
//			if (sender != null) {
////				msg.setFrom(new InternetAddress(sender, CommonUtils.isNull(
////						sendNickName, "船员系统")));
//
//			}
//			msg.setRecipients(Message.RecipientType.TO,
//					InternetAddress.parse(to, false));
//			if (cc != null) {
//				msg.setRecipients(Message.RecipientType.CC,
//						InternetAddress.parse(cc, false));
//			}
//			subject = "subjuect";
//			msg.setSubject(subject, "GBK");
//			msg.setText(content, "GBK");
//			msg.setSentDate(new Date());
//
//			// 附件处理 by pzh .add 2010-2-20
////			if (attachId != null) {
////				SH_AttachmentVO attachmentvo = FtpUtil
////						.getAttachmentBySysId(attachId);
//				if (attachId != null) {
////					String attachName = attachmentvo.getAttachName();
////					String remotePath = attachmentvo.getPath();
////					String remoteFile = attachmentvo.getFileName();
////					File attachFile = new File(remoteFile);
////					fos = new FileOutputStream(attachFile);
////					BufferedOutputStream bos = new BufferedOutputStream(fos);
////
////					FtpUtil.downloadFromFtpServer(bos, remotePath, remoteFile);
//
////					try {
////						bos.flush();
////						fos.flush();
////						fos.close();
////
////						Multipart multipart = new MimeMultipart();
////						// 设置邮件的文本内容
////						MimeBodyPart contentPart = new MimeBodyPart();
////						contentPart
////								.setContent(content, "text/html;charset=GBK");
////						multipart.addBodyPart(contentPart);
////
////						BodyPart affixBody = new MimeBodyPart();
////						DataSource source = new FileDataSource(attachFile);
////						// 添加附件的内容
////						affixBody.setDataHandler(new DataHandler(source));
////
////						// 中文文件名转码
////						affixBody.setFileName(MimeUtility
////								.encodeText(attachName));
////						multipart.addBodyPart(affixBody);
////						msg.setContent(multipart);
////					} catch (Exception e) {
////						throw e;
////					} finally {
////						bos = null;
////						fos = null;
////						attachFile = null;
////					}
////				}
////			}
//			msg.saveChanges();
//			Transport.send(msg);
//		} catch (Exception ex) {
//			throw new Exception(ex);
//		} finally {
//			if (fos != null) {
//				try {
//					fos.close();
//					fos = null;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

	private static String encString(String str) {
		if (str == null) {
			return null;
		}
		try {
			return new String(str.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException ex) {
			return str;
		}
	}

	/**
	 * 发送邮件(带附件)
	 * 
	 * @param subject
	 *            邮件标题
	 * @param content
	 *            邮件正文
	 * @param from
	 *            发件人
	 * @param to
	 *            收件人
	 * @param cc
	 *            抄送
	 * @throws Exception
	 *             应用异常
	 */
	public void sendMailOfAttach(String subject, String content, String to,
			String from, String cc, String attachName, String attachPath)
			throws Exception {
		try {
			Properties props = System.getProperties();

			props.put("mail.smtp.host", mailHost); // 邮件服务器
			props.put("mail.smtp.port", Integer.toString(mailHostPort)); // 邮件服务器端口

			props.put("mail.smtp.auth", "false"); // 指定是否需要SMTP验证
			props.put("mail.store.protocol", "pop3");
			props.put("mail.transport.protocol", "smtp");
			Session session = null;

			// if (SystemParameter.getParameterValueDefault(
			// "email.smtp.authrozer", "N").equals("Y")) {
			// session = Session.getDefaultInstance(props,
			// new Authenticator() {
			// protected PasswordAuthentication getPasswordAuthentication() {
			// return new PasswordAuthentication(user,
			// password);
			// }
			// });
			// } else {
			// session = Session.getDefaultInstance(props);
			// }

			session.setDebug(false); // 输出debug信息

			if (log.isInfoEnabled()) {
				log.info(" to mail: " + to);
			}

			MimeMessage msg = new MimeMessage(session);
			// remove by timpeng 2005-11-17
			if (from != null) {
				// msg.setFrom(new InternetAddress(sender));
				msg.setFrom(new InternetAddress(sender, "船员系统"));

			}
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			if (cc != null) {
				msg.setRecipients(Message.RecipientType.CC,
						InternetAddress.parse(cc, false));
			}
			// subject = SystemParameter.getParameterValueDefault(
			// "mailTitlePrefix", "船员系统邮件(测试):")
			// + subject;
			msg.setSubject(subject, "GBK");
			msg.setText(content, "GBK");
			msg.setSentDate(new Date());

			// 添加附件----------------
			Multipart multipart = new MimeMultipart();
			// 设置邮件的文本内容

			MimeBodyPart contentPart = new MimeBodyPart();

			contentPart.setContent(content, "text/html;charset=GBK");
			multipart.addBodyPart(contentPart);

			BodyPart affixBody = new MimeBodyPart();
			DataSource source = new FileDataSource(attachPath);

			// 添加附件的内容
			affixBody.setDataHandler(new DataHandler(source));
			// 添加附件的标题这里很重要，通过下面的Base64编码的转换可以保证你的
			// 中文附件标题名在发送时不会变成乱码
			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();

			String fileName = "=?GBK?B?"

			+ enc.encode(attachName.getBytes()) + "?=";

			affixBody.setFileName(fileName);

			multipart.addBodyPart(affixBody);
			// 将multipart对象放到message中
			msg.setContent(multipart);

			msg.saveChanges();
			Transport.send(msg);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	/**
	 * 发送多个email
	 * 
	 * @param list
	 *            email列表
	 * @throws Exception
	 *             应用异常
	 */
	private void sendMailList(List list) throws Exception {
		// if (list == null || list.isEmpty()) {
		// return;
		// }
		//
		// String userId = "";
		// String tomail = "";
		// SqlClt sqlclt = SqlCltFactory.getDefaultSqlClt();
		// String attachFlag = null;
		// String sender = null;
		// String senderNickname = null;
		// for (MMT_MESSAGESVO messagevo : list) {
		// try {
		// List<MMT_MESSAGE_RECEIVERSVO> recList = MMT_MESSAGE_RECEIVERSDAO
		// .getInstance().selectList(
		// "MESSAGE_ID='" + messagevo.getID() + "'");
		// messagevo.setSEND_EMAIL(Integer.valueOf("0")); // fail
		// attachFlag = null;
		// sender = messagevo.getSENDER();
		// if (sender != null && sender.length() > 5) {
		// sender = sender.substring(5);
		// } else {
		// sender = null;
		// }
		// if (sender != null) {
		// MMT_USERVO sendervo = MMT_USERDAO.getInstance().fetch(
		// "id='" + sender + "'");
		// senderNickname = sendervo.getNAME();
		// }
		// if (messagevo.getAttachmentFlag() != null
		// && "1".equalsIgnoreCase(messagevo.getAttachmentFlag())) {
		// attachFlag = messagevo.getID();
		// }
		//
		// for (MMT_MESSAGE_RECEIVERSVO receivervo : recList) {
		// userId = receivervo.getRECEIVER();
		// if (userId.indexOf("@") > 0) {
		// tomail = userId;
		// } else if (userId != null && userId.length() > 5) {
		// userId = userId.substring(5);
		// MMT_USERVO uservo = MMT_USERDAO.getInstance().fetch(
		// "id='" + userId + "'");
		// if (uservo != null) {
		// tomail = uservo.getEmail();
		// }
		// }
		// tomail = tomail == null ? "" : tomail;
		// tomail = tomail.trim();
		// if (tomail.indexOf("@") > 0) {
		// try {
		// sendMail(messagevo.getTITLE(), messagevo
		// .getMESSAGE(), tomail, null, null,
		// attachFlag, senderNickname);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// log.error("send mail fail", e);
		// }
		// messagevo.setSEND_EMAIL(Integer.valueOf("1")); // success
		// }
		// }
		// } catch (Exception e) {
		// sqlclt.rollback();
		// log.error("send mail fail", e);
		// messagevo.setSEND_EMAIL(Integer.valueOf("5")); // fail
		// } finally {
		// MMT_MESSAGESDAO.getInstance().update(messagevo);
		// sqlclt.commit();
		// }
		// }

	}

	/**
	 * 发送email
	 * 
	 * @throws Exception
	 *             应用异常
	 */
	public synchronized void sendMails() {
		// SqlClt sqlclt = SqlCltFactory.getDefaultSqlClt();
		// try {
		// // 处理异常数据状态 2010-05-04
		// sqlclt
		// .execUpdate("update MMT_MESSAGES set message=' ' where SEND_EMAIL is null and datalength(message)=0");
		// String sql = "select top " + max
		// + " * from MMT_MESSAGES where SEND_EMAIL is null ";
		// List<MMT_MESSAGESVO> list = sqlclt
		// .select(sql, MMT_MESSAGESVO.class);
		//
		// if (log.isInfoEnabled()) {
		// log.info("开始处理发送邮件...........总份数:" + list.size());
		// }
		// sendMailList(list);
		// sqlclt.commit();
		// if (log.isInfoEnabled()) {
		// log.info("结束处理发送邮件. OK!!");
		// }
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// try{
		// sqlclt.rollback();
		// }catch(Exception e){
		// log.error("send mail fail", e);
		// }
		// log.error("send mail fail", ex);
		// }
	}

	public void run() {

		logger.info(" email send begin .........");
		EmailServer es = new EmailServer();
		es.sendMails();
		logger.info(" email send end .........");
	}

	/**
	 * 主程序
	 * 
	 * @param args
	 *            参数
	 */
	public static void main(String[] args) throws Exception {
		EmailServer es = new EmailServer();
		// es.run();
		// es.sendMailOfAttach("测试", "测试12 from coscocyxt1",
		// "dacheng@coscogz.com.cn", null, "","000091.xls","D:\\000091.xls");
		es.sendMail("coscol测试", "测试11 from coscocyxt11234",
				"zhongyq@zdnst.com", null, null, null, "test");
		System.out.println("email test ok..........");
		// System.exit(0);
	}
}
