package util.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CreateMsgSql {

	public static void main(String[] args) throws Exception{
		String inputFileName = "D:\\code\\msgSql_input.txt";
		String outputFileName = "D:\\code\\msgSql_output.txt";
		File file = new File(inputFileName);
		BufferedReader reader = null;
		String s = null;
		String s1 = "MERGE INTO MESSAGE_PRPT USING DUAL ON (MSG_TYPE = '";
		String s2 = "' AND LOCALE = 'zh_CN' AND MSG_KEY = '";
		String s3 = "') WHEN MATCHED THEN UPDATE SET MSG_VALUE = '";
		String s4 = "', MOD_DATE = SYSDATE WHEN NOT MATCHED THEN INSERT (MSG_TYPE, LOCALE, MSG_KEY, MSG_VALUE) VALUES ('";
		String s5 ="', 'zh_CN', '";
		String s6 = "', '";
		String s7 = "');";
		String initType = "";
		String[] types = 
		{
				"bocnet.express.config.msg.bfw_error",
				"com.bocnet.client.config.msg.bfw_error",
				"com.bocnet.bii.config.msg.dictionary",
				"com.bocnet.core.config.msg.constants",
				"com.bocnet.b2e.config.msg.rspmsg",
				"com.bocnet.bii.config.msg.bfw_error",
				"com.bocnet.bii.config.msg.download_titles",
				"com.bocnet.bii.config.msTg.bfw_error",
				"com.bocnet.client.config.msg.menu",
				"com.bocnet.client.config.msg.page",
				"com.bocnet.core.config.msg.constants_en_US",
				"com.bocnet.wap.config.msg.bfw_error",
				"com.bocnet.home.config.msg.bfw_error",
				"com.bocnet.wap.config.msg.mbs_menu",
				"com.bocnet.wap.config.msg.message",
				"com.bocnet.client.config.msg.download_titles",
				"com.bocnet.mgmt.config.msg.message",
				"com.bocnet.client.config.msg.externUrl",
				"com.bocnet.bii.query.chargeRate",
				"com.bocnet.bii.query.chargeStartDate",
				"com.bocnet.bii.query.tokenId",
				"com.bocnet.client.config.msg.rate_url",
				"com.bocnet.core.config.msg.constants_ko_KR",
				"com.bocnet.core.config.msg.constants_zh_CN",
				"com.bocnet.wap.config.msg.dictionary",
				"com.bocnet.errpro.config.msg.bfw_error",
				"com.bocnet.client.config.msg.message_zh_CN",
				"com.bocnet.home.config.msg.dictionary",
				"com.bocnet.wap.config.msg.oversea_service",
				"com.bocnet.home.config.msg.hbs_menu",
				"com.bocnet.info.config.msg.bfw_error",
				"com.bocnet.bii.config.msg.externUrl",
				"cpaw.paraConsign.notemail.error",
				"svr.log.summary.ent.cass.contract.sign",
				"svr.log.summary.ent.cass.contract.verifyPass",
				"com.bocnet.bii.query.PayNumber",
				"com.bocnet.bii.query.accountName",
				"com.bocnet.core.config.msg.bfw_error",
				"com.bocnet.client.config.msg.jsp_message",
				"com.bocnet.core.config.msg.exception",
				"com.bocnet.b2e.config.msg.dictionary",
				"com.bocnet.bii.oversea.msg",
				"com.bocnet.b2e.config.msg.rspcod",
				"com.bocnet.client.config.msg.message",
				"com.bocnet.bii.config.msg.message",
				"com.bocnet.wap.config.msg.constants",
				"com.bocnet.bii.config.msg",
				"com.bocnet.client.config.msg.oversea_service",
				"com.bocnet.bii.config.msg.download_title",
				"com.bocnet.home.config.msg.constants",
				"com.bocnet.client.config.msg.dictionary",
				"com.bocnet.errpro.config.msg.constants",
				"com.bocnet.receiver.warning.config.warning",
				"com.bocnet.register.config.msg.constants",
				"com.bocnet.bii.query.accountNumber",
				"com.bocnet.bii.query.chargeName",
				"com.bocnet.bii.query.clientCert",
				"com.bocnet.bii.query.discountRate",
				"com.bocnet.bii.query.name",
				"com.bocnet.bii.query.operatorId",
				"com.bocnet.bii.query.ownedEnterprises",
				"com.bocnet.b2e.config.msg.bfw_error",
				"com.bocnet.client.config.msg.message_en_US",
				"com.bocnet.core.config.msg.dictionary",
				"com.bocnet.core.config.msg.constants_ja_JP",
				"com.bocnet.wap.config.msg.jsp_message",
				"com.bocnet.client.config.msg.global_message",
				"com.bocnet.core.config.msg.message",
				"com.bocnet.wap.config.msg.download_titles",
				"com/bocnet/errpro/config/msg/bfw_error",
				"validation.SalaryBatchConsign.ErrorSumNum",
				"validation.ent.operator.below.accountList.empty",
				"validation.protocolNoOrContractNo.needOnlyOneError",
				"com.bocnet.bii.query.PayNumberName"	
		};
		int j = 0;//行号，方便定位文件哪一行有误
		FileWriter fw = new FileWriter(outputFileName);
		PrintWriter pw = new PrintWriter(fw);;
		try {
			FileReader fis = new FileReader(file);
			reader = new BufferedReader(fis);
			//读取文件
			while ((s = reader.readLine()) != null) {
				j++;
				boolean bl = false;
				for(int i=0;i<types.length;i++){
					String type = types[i];
					if(s.equals(type)){
						initType = s;
						bl = true;
					}
				}
				String sqlOut = "--" + initType;
				if(!bl){
					String[] dates = s.split("	");
					//判断文件格式
					if(dates.length!=2){
						throw new RuntimeException("第"+j+"文件内容格式有误！");
					}
					String param = dates[0];//参数名
					String note = dates[1];//注释
					sqlOut = s1+ initType +s2 + param + s3 + note + s4 + initType +s5 + param + s6 + note + s7;
				}
				System.out.println(sqlOut);
				pw.println(sqlOut);
	        }
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}finally {
			reader.close();
			pw.close();
		}
	}
}
