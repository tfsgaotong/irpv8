package com.tfs.irp.util.wordfilter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.tfs.irp.util.wordfilter.result.FilteredResult;
import com.tfs.irp.util.wordfilter.search.tree.Node;
public class WordFilterUtil {

	private static Node tree;

	static {
		tree = new Node();
		InputStream is = WordFilterUtil.class.getResourceAsStream("/sensitive/words.dict");
		try {
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");
			Properties prop = new Properties();
			prop.load(reader);
			Enumeration<String> en = (Enumeration<String>)prop.propertyNames();
			while(en.hasMoreElements()){
				String word = en.nextElement();
				insertWord(word,Integer.valueOf(prop.getProperty(word)));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private static void insertWord(String word,int level){
		Node node = tree;
		for(int i=0;i<word.length();i++){
			node = node.addChar(word.charAt(i));
		}
		node.setEnd(true);
		node.setLevel(level); 
	}

	private static boolean isPunctuationChar(String c) {
		String regex = "[\\pP\\pZ\\pS\\pM\\pC]";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(c);
		return m.find();
	}

	private static PunctuationOrHtmlFilteredResult filterPunctation(String originalString){
		StringBuffer filteredString=new StringBuffer();
		ArrayList<Integer> charOffsets=new ArrayList<Integer>();
		for(int i=0;i<originalString.length();i++){
			String c = String.valueOf(originalString.charAt(i));
			if (!isPunctuationChar(c)) {
				filteredString.append(c);
				charOffsets.add(i);
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString(originalString);
		result.setFilteredString(filteredString);
		result.setCharOffsets(charOffsets);
		return result;
	}

	private static PunctuationOrHtmlFilteredResult filterPunctationAndHtml(String originalString){
		StringBuffer filteredString=new StringBuffer();
		ArrayList<Integer> charOffsets=new ArrayList<Integer>();
		for(int i=0,k=0;i<originalString.length();i++){
			String c = String.valueOf(originalString.charAt(i));
			if (originalString.charAt(i) == '<') {
				for(k=i+1;k<originalString.length();k++) {
					if (originalString.charAt(k) == '<') {
						k = i;
						break;
					}
					if (originalString.charAt(k) == '>') {
						break;
					}
				}
				i = k;
			} else {
				if (!isPunctuationChar(c)) {
					filteredString.append(c);
					charOffsets.add(i);
				}
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString(originalString);
		result.setFilteredString(filteredString);
		result.setCharOffsets(charOffsets);
		return result;
	}

	private static FilteredResult filter(PunctuationOrHtmlFilteredResult pohResult, char replacement){
		StringBuffer sentence =pohResult.getFilteredString();
		ArrayList<Integer> charOffsets = pohResult.getCharOffsets();
		StringBuffer resultString = new StringBuffer(pohResult.getOriginalString());
		StringBuffer badWords = new StringBuffer();
		int level=0;
		Node node = tree;
		int start=0,end=0;
		for(int i=0;i<sentence.length();i++){
			start=i;
			end=i;
			node = tree;
			for(int j=i;j<sentence.length();j++){
				node = node.findChar(sentence.charAt(j));
				if(node == null){
					break;
				}
				if(node.isEnd()){
					end=j;
					level = node.getLevel();
				}
			}
			if(end>start){
				for(int k=start;k<=end;k++){
					resultString.setCharAt(charOffsets.get(k), replacement);
				}
				if(badWords.length()>0)badWords.append(",");
				badWords.append(sentence.substring(start, end+1));
				i=end;
			}
		}
		FilteredResult result = new FilteredResult();
		result.setOriginalContent(pohResult.getOriginalString());
		result.setFilteredContent(resultString.toString());
		result.setBadWords(badWords.toString());
		result.setLevel(level);
		return result;
	}
	
	/**
	 * 简单句子过滤
	 * 不处理特殊符号，不处理html，简单句子的过滤
	 * 不能过滤中间带特殊符号的关键词，如：黄_色_漫_画
	 * @param sentence 需要过滤的句子
	 * @param replacement 关键词替换的字符
	 * @return 过滤后的句子
	 */
	public static String simpleFilter(String sentence, char replacement){
		StringBuffer sb=new StringBuffer();
		Node node = tree;
		int start=0,end=0;
		for(int i=0;i<sentence.length();i++){
			start=i;
			end=i;
			node = tree;
			for(int j=i;j<sentence.length();j++){
				node = node.findChar(sentence.charAt(j));
				if(node == null){
					break;
				}
				if(node.isEnd()){
					end=j;
				}
			}
			if(end>start){
				for(int k=start;k<=end;k++){
					sb.append(replacement);
				}
				i=end;
			}else{
				sb.append(sentence.charAt(i));
			}
		}
		return sb.toString();
	}
	/**
	 * 纯文本过滤，不处理html标签，直接将去除所有特殊符号后的字符串拼接后进行过滤，可能会去除html标签内的文字，比如：如果有关键字“fuckfont”，过滤fuck<font>a</font>后的结果为****<****>a</font>
	 * @param originalString 原始需过滤的串
	 * @param replacement 替换的符号
	 * @return
	 */
	public static FilteredResult filterText(String originalString, char replacement){
		return filter(filterPunctation(originalString), replacement);
	}
	/**
	 * html过滤，处理html标签，不处理html标签内的文字，略有不足，会跳过<>标签内的所有内容，比如：如果有关键字“fuck”，过滤<a title="fuck">fuck</a>后的结果为<a title="fuck">****</a>
	 * @param originalString 原始需过滤的串
	 * @param replacement 替换的符号
	 * @return
	 */
	public static FilteredResult filterHtml(String originalString, char replacement){
		return filter(filterPunctationAndHtml(originalString), replacement);
	}
	/**
	 * 3、如需自定义词库，将jar包考入WEB-INF工程的lib目录，在WEB-INF/classes目录下建一个
		utf-8的words.dict文本文件，在该文件中以“关键字=级别”的方式写入，比如：
		hello=4
		word=1
		0为级别最小，过滤后返回原字符串中出现的最高级别
	 * @param args
	 */
	public static void main(String[] args){
		Long starttime=System.currentTimeMillis();
		String str  = "禁止词</font>正常词<font color=\"#1f376d\">敏感词";
		System.out.println("知识长度"+str.length());
		FilteredResult result = WordFilterUtil.filterHtml(str,'*');
		Long endtime=System.currentTimeMillis();
		System.out.println("执行需要时间："+(endtime-starttime)+" 毫秒");
		System.out.println("original:"+result.getOriginalContent());
		System.out.println("result:"+result.getFilteredContent());
		System.out.println("badWords:"+result.getBadWords());
	}
 	private static class PunctuationOrHtmlFilteredResult {
		private String originalString;
		private StringBuffer filteredString;
		private ArrayList<Integer> charOffsets;
		
		public String getOriginalString() {
			return originalString;
		}
		public void setOriginalString(String originalString) {
			this.originalString = originalString;
		}
		public StringBuffer getFilteredString() {
			return filteredString;
		}
		public void setFilteredString(StringBuffer filteredString) {
			this.filteredString = filteredString;
		}
		public ArrayList<Integer> getCharOffsets() {
			return charOffsets;
		}
		public void setCharOffsets(ArrayList<Integer> charOffsets) {
			this.charOffsets = charOffsets;
		}
	}

}
