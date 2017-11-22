package com.tfs.irp.util.textrank;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;

/**
 * TextRank 自动摘要
 * 
 * @author hankcs
 */
public class TextRankSummary {
	/**
	 * 阻尼系数（ＤａｍｐｉｎｇＦａｃｔｏｒ），一般取值为0.85
	 */
	private final double d = 0.85f;
	/**
	 * 最大迭代次数
	 */
	private final int max_iter = 200;
	private final double min_diff = 0.001f;
	/**
	 * 文档句子的个数
	 */
	private int D;
	/**
	 * 拆分为[句子[单词]]形式的文档
	 */
	private List<List<String>> docs;
	/**
	 * 排序后的最终结果 score <-> index
	 */
	private TreeMap<Double, Integer> top;

	/**
	 * 句子和其他句子的相关程度
	 */
	private double[][] weight;
	/**
	 * 该句子和其他句子相关程度之和
	 */
	private double[] weight_sum;
	/**
	 * 迭代之后收敛的权重
	 */
	private double[] vertex;

	/**
	 * BM25相似度
	 */
	private BM25 bm25;

	private TextRankSummary(List<List<String>> docs) {
		this.docs = docs;
		bm25 = new BM25(docs);
		D = docs.size();
		weight = new double[D][D];
		weight_sum = new double[D];
		vertex = new double[D];
		top = new TreeMap<Double, Integer>(Collections.reverseOrder());
		solve();
	}

	private void solve() {
		int cnt = 0;
		for (List<String> sentence : docs) {
			double[] scores = bm25.simAll(sentence);
			// System.out.println(Arrays.toString(scores));
			weight[cnt] = scores;
			weight_sum[cnt] = sum(scores) - scores[cnt]; // 减掉自己，自己跟自己肯定最相似
			vertex[cnt] = 1.0;
			++cnt;
		}
		for (int _ = 0; _ < max_iter; ++_) {
			double[] m = new double[D];
			double max_diff = 0;
			for (int i = 0; i < D; ++i) {
				m[i] = 1 - d;
				for (int j = 0; j < D; ++j) {
					if (j == i || weight_sum[j] == 0)
						continue;
					m[i] += (d * weight[j][i] / weight_sum[j] * vertex[j]);
				}
				double diff = Math.abs(m[i] - vertex[i]);
				if (diff > max_diff) {
					max_diff = diff;
				}
			}
			vertex = m;
			if (max_diff <= min_diff)
				break;
		}
		// 我们来排个序吧
		for (int i = 0; i < D; ++i) {
			top.put(vertex[i], i);
		}
	}

	/**
	 * 获取前几个关键句子
	 * 
	 * @param size
	 *            要几个
	 * @return 关键句子的下标
	 */
	private int[] getTopSentence(int size) {
		Collection<Integer> values = top.values();
		size = Math.min(size, values.size());
		int[] indexArray = new int[size];
		Iterator<Integer> it = values.iterator();
		for (int i = 0; i < size; ++i) {
			indexArray[i] = it.next();
		}
		return indexArray;
	}

	/**
	 * 简单的求和
	 * 
	 * @param array
	 * @return
	 */
	private static double sum(double[] array) {
		double total = 0;
		for (double v : array) {
			total += v;
		}
		return total;
	}

	/**
	 * 将文章分割为句子
	 * 
	 * @param document
	 * @return
	 */
	private static List<String> spiltSentence(String document) {
		List<String> sentences = new ArrayList<String>();
		if (document == null)
			return sentences;
		for (String line : document.split("[\r\n]")) {
			line = line.trim();
			if (line.length() == 0)
				continue;
			for (String sent : line.split("[。.；;]")) {
				sent = sent.trim();
				if (sent.length() == 0)
					continue;
				sentences.add(sent);
			}
		}

		return sentences;
	}

	/**
	 * 是否应当将这个term纳入计算，词性属于名词、动词、副词、形容词
	 * 
	 * @param term
	 * @return 是否应当
	 */
	private static boolean shouldInclude(Term term) {
		if (term.getNatrue().natureStr.startsWith("n")
				|| term.getNatrue().natureStr.startsWith("v")
				|| term.getNatrue().natureStr.startsWith("d")
				|| term.getNatrue().natureStr.startsWith("a")) {
			// TODO 你需要自己实现一个停用词表
			// if (!StopWordDictionary.contains(term.getName()))
			// {
			return true;
			// }
		}

		return false;
	}

	/**
	 * 一句话调用接口
	 * 
	 * @param document
	 *            目标文档
	 * @param size
	 *            需要的关键句的个数
	 * @return 关键句列表
	 */
	public static List<String> getTopSentenceList(String document, int size) {
		List<String> sentenceList = spiltSentence(document);
		List<List<String>> docs = new ArrayList<List<String>>();
		for (String sentence : sentenceList) {
			List<Term> termList = ToAnalysis.parse(sentence);
			List<String> wordList = new LinkedList<String>();
			for (Term term : termList) {
				if (shouldInclude(term)) {
					wordList.add(term.getRealName());
				}
			}
			docs.add(wordList);
		}
		TextRankSummary textRankSummary = new TextRankSummary(docs);
		int[] topSentence = textRankSummary.getTopSentence(size);
		List<String> resultList = new LinkedList<String>();
		for (int i : topSentence) {
			resultList.add(sentenceList.get(i));
		}
		return resultList;
	}
}
