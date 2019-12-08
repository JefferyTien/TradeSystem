package com.reddoor.tradesystem.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Ionic3UpgradeIonic4 {

	public Map<String, String> classRouteMapping = new HashMap<String, String>();
	
	public static void main(String[] args) {
		// 功能写完之后先以一个最简单的文件夹做测试, 试试路由是否管用
//		File sourceDir = new File("E:/temp/hhhhhhhhhhhhhhh");
//		Ionic3UpgradeIonic4 upgrader = new Ionic3UpgradeIonic4();
//		upgrader.upgradeSourceDir(sourceDir);
		
		File sourceDir = new File("E:/temp/hhhhhhhhhhhhhhh");
		Ionic3UpgradeIonic4 upgrader = new Ionic3UpgradeIonic4();
		upgrader.produceClassRouteMapping(sourceDir);
		
		File specialFile = new File("E:/temp/gggggggggggggggg/sideMenu.ts");
		upgrader.dealWithTSFile(specialFile);
		
	}
	
	
	public void upgradeSourceDir(File sourceDir){
		
		// 遍历所有文件, 生成classRouteMapping
		produceClassRouteMapping(sourceDir);
		// 打印路由
		outputClassRouteMapping();
				
				
		if(sourceDir.exists() && sourceDir.isDirectory()){
			File[] subFiles = sourceDir.listFiles();
			for(File eachFile : subFiles){
				if(eachFile.isDirectory()){
					upgradeSourceDir(eachFile);
				}
				else {
					dealWithFile(eachFile);
				}
			}
		}
	}
	
	public void outputClassRouteMapping(){
		for(Map.Entry<String, String> eachEntry : classRouteMapping.entrySet()){
			String eachRoute = eachEntry.getKey() +"   " + eachEntry.getValue();
			System.out.println(eachRoute);
		}
	}
	
	public void produceClassRouteMapping(File file){
		if(file.exists() && file.isDirectory()){
			File[] subFiles = file.listFiles();
			for(File eachFile : subFiles){
				if(eachFile.isDirectory()){
					produceClassRouteMapping(eachFile);
				}
				else {
					String fileName = eachFile.getName();
					String parentName = eachFile.getParentFile().getName();
					
					if(fileName.endsWith(".module.ts") || fileName.endsWith(".html") || fileName.endsWith(".scss") || !fileName.endsWith(".ts")){
						continue;
					}
					String shortName = fileName.substring(0, fileName.indexOf("."));
					FileInputStream fis = null;
					InputStreamReader isr = null;
					BufferedReader bReader = null;
					try {
						String line;
						fis = new FileInputStream(eachFile);// 定义输入文件
						isr = new InputStreamReader(fis, "utf-8");// 读取输入文件
						bReader = new BufferedReader(isr);// 读取缓冲区

						String className = "";
						while ((line = bReader.readLine()) != null) { // 按行读取数据
							if (line.indexOf("export class ") >= 0) {
								className = line.replace("export class ", "").replace("{", "").trim();
								classRouteMapping.put(className, "/"+parentName+"/"+shortName);
								break;
							}
						}
						
					}
					catch (FileNotFoundException e) {

					}
					catch (IOException e) {

					}
					finally {
						try {
							bReader.close();// 关闭读取缓冲区
							isr.close();// 关闭读取文件内容
							fis.close();// 关闭读取文件
						}
						catch (IOException e) {
						}
					}
					
				}
			}
		}
	}
	
	public void dealWithFile(File file){
		String fileName = file.getName();
		
		// 处理文件内容
		if(fileName.endsWith(".module.ts")){
//			dealWithModuleFile(file);
		}
		else if(fileName.endsWith(".ts")){
			// 对于.ts文件, 找到@Component, 增加styleUrls, 处理 navCtrl.push(
//			dealWithTSFile(file);
			
			// 增加 处理navCtrl.setRoot,  每个.ts里 constructor加上router
			dealWithAdditionalIssues(file);
		}
		else{
			// .html  .scss 文件不处理
		}
		
		
//		writeFile(file);
 
	}
	
	public void dealWithModuleFile(File file){
		String fileName = file.getName();
		String shortName = fileName.substring(0, fileName.indexOf("."));
		String content = readFile(file);
		String resultStr = "";
		int moduleIndex = content.indexOf("@NgModule");
		if(moduleIndex>=0){
			String import1 = "import { SharedModule } from '../../shared';\n";
			String import2 = "import { RouterModule } from '@angular/router';\n";
			resultStr = content.substring(0, moduleIndex) + import1 + import2 + content.substring(moduleIndex);
		}
		
		String className = "";
		int declareIndex = content.indexOf("declarations:");
		if(declareIndex>=0){
			String tempStr = content.substring(declareIndex);
			className = tempStr.substring(tempStr.indexOf("[")+1, tempStr.indexOf("]")).trim();
		}
		String importStr = "imports: [SharedModule,RouterModule.forChild([{ path: '"+shortName+"', component: "+className+" }])]";
		resultStr = resultStr + "\n"+ importStr;
		
		writeFile(file, resultStr);
	}
	
	public void dealWithTSFile(File file) {
		String fileName = file.getName();
		String parentDirName = file.getParentFile().getName();
		String styleUrl = "";
		String shortName = fileName.substring(0, fileName.indexOf("."));
		styleUrl = "styleUrls: ['" + shortName + ".scss" + "'],";
		
		StringBuffer resultSb = new StringBuffer();
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		try {
			String line;
			fis = new FileInputStream(file);// 定义输入文件
			isr = new InputStreamReader(fis, "utf-8");// 读取输入文件
			bReader = new BufferedReader(isr);// 读取缓冲区

			String lineDealt = "";
			while ((line = bReader.readLine()) != null) { // 按行读取数据
				if(line.indexOf("selector:'") >= 0 || line.indexOf("selector: '") >= 0 || line.indexOf("selector:\"") >= 0 || line.indexOf("selector: \"") >= 0){
					if(line.endsWith(",")){
						lineDealt = line + "\n" + styleUrl;
					}
					else{
						lineDealt = line +","+ "\n" + styleUrl;
					}
				}
				else if((line.indexOf("navCtrl.push(") >= 0 || line.indexOf("this.nav.push(") >= 0 || line.indexOf("this.nav.setRoot(") >= 0 )&& !line.startsWith("//") && !line.trim().startsWith("//")){
					
					// 转换路由
					String pagePara = "";
					int firstQuota = line.indexOf("\"");
					String route = "";
					if(firstQuota>=0){
						int secondQuota = firstQuota + (line.substring(firstQuota+1)).indexOf("\"")+1;
						pagePara = line.substring(firstQuota+1, secondQuota);
						route = classRouteMapping.get(pagePara);
					}
					
					// 提取参数
					int firstBrace = line.indexOf("{");
					int lastBrace = line.lastIndexOf("}");
					String oldPara = "";
					if(firstBrace>=0){
						oldPara = line.substring(firstBrace+1, lastBrace);
					}
					String routePara = "";
					if(!"".equals(oldPara)){
						routePara = ",{queryParams:{"+oldPara+"} }";
					}
					
					String routerTemplate = "";
					if(!"".equals(route)){
						routerTemplate = "this.router.navigateByUrl('"+route+"'"+routePara+");";
					}
					else {
						routerTemplate = "this.router.navigateByUrl(''"+routePara+");";
					}
					// 注掉原来的 navCtrl.push
					lineDealt = line + "\n" +routerTemplate;
				}
				else{
					lineDealt=line;
				}
				
				resultSb.append(lineDealt).append("\n");
			}
			
			writeFile(file, resultSb.toString());
		}
		catch (FileNotFoundException e) {

		}
		catch (IOException e) {

		}
		finally {
			try {
				bReader.close();// 关闭读取缓冲区
				isr.close();// 关闭读取文件内容
				fis.close();// 关闭读取文件
			}
			catch (IOException e) {
			}
		}
	}
	
	public void dealWithAdditionalIssues(File file){
		String fileName = file.getName();
		String parentDirName = file.getParentFile().getName();
		String shortName = fileName.substring(0, fileName.indexOf("."));
		
		StringBuffer resultSb = new StringBuffer();
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		try {
			String line;
			fis = new FileInputStream(file);// 定义输入文件
			isr = new InputStreamReader(fis, "utf-8");// 读取输入文件
			bReader = new BufferedReader(isr);// 读取缓冲区

			String lineDealt = "";
			while ((line = bReader.readLine()) != null) { // 按行读取数据
				if(line.indexOf("navCtrl.setRoot(") >= 0 && !line.startsWith("//") && !line.trim().startsWith("//")){
					
					// 转换路由
					String pagePara = "";
					int firstQuota = line.indexOf("\"");
					String route = "";
					if(firstQuota>=0){
						int secondQuota = firstQuota + (line.substring(firstQuota+1)).indexOf("\"")+1;
						pagePara = line.substring(firstQuota+1, secondQuota);
						route = classRouteMapping.get(pagePara);
					}
					
					// 提取参数
					int firstBrace = line.indexOf("{");
					int lastBrace = line.lastIndexOf("}");
					String oldPara = "";
					if(firstBrace>=0){
						oldPara = line.substring(firstBrace+1, lastBrace);
					}
					String routePara = "";
					if(!"".equals(oldPara)){
						routePara = ",{queryParams:{"+oldPara+"} }";
					}
					
					String routerTemplate = "";
					if(!"".equals(route)){
						routerTemplate = "this.navCtrl.navigateRoot('"+route+"'"+routePara+");";
					}
					else {
						routerTemplate = "this.navCtrl.navigateRoot(''"+routePara+");";
					}
					
					lineDealt = line + "\n" + routerTemplate;
				}
				else{
					lineDealt=line;
				}
				
				resultSb.append(lineDealt).append("\n");
			}
			
			writeFile(file, resultSb.toString());
		}
		catch (FileNotFoundException e) {

		}
		catch (IOException e) {

		}
		finally {
			try {
				bReader.close();// 关闭读取缓冲区
				isr.close();// 关闭读取文件内容
				fis.close();// 关闭读取文件
			}
			catch (IOException e) {
			}
		}
	}
	
	
	public String readFile(File file){
		Long fileLength = file.length();
		byte[] fileContext = new byte[fileLength.intValue()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			in.read(fileContext);
			// 避免出现中文乱码
			String str = new String(fileContext, "utf-8");
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public void writeFile(File file, String content){
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	

}
