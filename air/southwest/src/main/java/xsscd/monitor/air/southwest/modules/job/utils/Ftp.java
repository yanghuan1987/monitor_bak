/**
 * 
 */
package xsscd.monitor.air.southwest.modules.job.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.net.SocketException;

/**
 * @author prominentl
 *
 */
public class Ftp {
	private FTPClient ftpClient;
	private String strIp;
	private int intPort;
	private String user;
	private String password;

	public Ftp(String strIp, int intPort, String user, String Password) {
		this.strIp = strIp;
		this.intPort = intPort;
		this.user = user;
		this.password = Password;
		this.ftpClient = new FTPClient();
	}
	
	/**
	 * 连接FTP
	 * @throws SocketException
	 * @throws IOException
	 */
	public void connect() throws SocketException, IOException{
		ftpClient.connect(strIp, intPort);
		ftpClient.login(user, password);
		ftpClient.enterLocalPassiveMode();  
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	}

	/**
	 *
	 * @param localDires
	 *            本地的保存目录
	 * @param remoteDownLoadPath
	 *            远端的文件目录
	 * @param remoteFileName
	 *            远端要取得文件名
	 * @return
	 * @throws Exception 
	 */
	public boolean downloadFile(String localDires, String remoteDownLoadPath, String remoteFileName) throws Exception {
		
		boolean bln = false ; 
		
		if (!localDires.endsWith(File.separator)) {
			localDires = localDires + File.separator;
		}
		
		File localFile = new File(localDires);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		
		String strFilePath = localDires  + remoteFileName;
		BufferedOutputStream outStream = null;
		try {
			this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);
			outStream = new BufferedOutputStream(new FileOutputStream(strFilePath));
			//TODO 需要判断有没有这个文件
			bln = this.ftpClient.retrieveFile(remoteFileName, outStream);
			return bln;
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != outStream) {
				try {
					outStream.flush();
					outStream.close();
				} catch (IOException e) {
					// IGNORE
				}
			}
		}
	}

	/***
	 * @下载文件夹
	 * @param localDirectoryPath 本地地址
	 * @param remoteDirectory 远程文件夹
	 * */
	public boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {
		try {
			String fileName = new File(remoteDirectory).getName();
			localDirectoryPath = localDirectoryPath + fileName + "//";
			new File(localDirectoryPath).mkdirs();
			FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);
			for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
				if (!allFile[currentFile].isDirectory()) {
					downloadFile(allFile[currentFile].getName(),localDirectoryPath, remoteDirectory);
				}
			}
			for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
				if (allFile[currentFile].isDirectory()) {
					String strremoteDirectoryPath = remoteDirectory + "/"+ allFile[currentFile].getName();
					downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	/**
	 * 读取流文件
	 * @param remoteDownLoadPath
	 * @param remoteFileName
	 * @return
	 * @throws IOException
	 */
	public  InputStream getFileStream(String remoteDownLoadPath, String remoteFileName) throws IOException{
		ftpClient.changeWorkingDirectory(remoteDownLoadPath);
		return ftpClient.retrieveFileStream(remoteFileName);
	}

	/**
	 * 关闭连接
	 */
	public void ftpLogOut() {
		if (null != this.ftpClient && this.ftpClient.isConnected()) {
			try {
				boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
				if (reuslt) {
				}
			} catch (IOException e) {
			} finally {
				try {
					this.ftpClient.disconnect();// 关闭FTP服务器的连接
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
