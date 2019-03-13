package cn.zhangjingyao.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class CustomizeEcharts {
	
	private static int LINE_NUM_EACH_ROW = 12; // 图例中每行显示的线条数目；
	private static int DEFAULT_LINE_NUM = 6; // 采用默认grid.top值的默认线条数目；
	private static int DEFAULT_GRID_TOP_PECENTAGE = 18; // 默认的grid.top百分比（整数部分）；
	private static int DELTA_GRID_TOP_PECENTAGE = 5; // 超出默认线条数时的grid.top百分比增量（整数部分）；
	private static int MAX_GRID_TOP_PECENTAGE = 50; // 最大的grid.top百分比（整数部分）；

	/**
	 * 调整图例显示样式 {图例太多时，Echarts文档注明: 特殊字符串 ''（空字符串）或者 '\n' (换行字符串)用于图例的换行。}
	 * 
	 * @param beforeData
	 * @return afterData
	 */
	public static List<String> adjustLegendData(List<String> beforeData) {
		int index = 0;
		int arrayLength = beforeData.size();
		List<String> afterData = new ArrayList<String>();

		for (int i = 0; i < arrayLength; i++) {
			if ((index + 1) % (LINE_NUM_EACH_ROW + 1) == 0) {
				afterData.add("\n");
				index++;
				afterData.add(beforeData.get(i));
			} else {
				afterData.add(beforeData.get(i));
			}
			index++;
		}
		return afterData;

	}

	/**
	 * 设置grid.top值
	 * 
	 * @param legendData
	 * @return
	 */
	public static String setGridTop(List<String> legendData) {

		int topInt;
		String gridTop;
		int len = legendData.size();

		// 如果图例太多，需要调整option中的grid.top值才能避免重叠
		topInt = (int) (len > DEFAULT_LINE_NUM
				? DEFAULT_GRID_TOP_PECENTAGE
						+ DELTA_GRID_TOP_PECENTAGE * (Math.ceil((len - DEFAULT_LINE_NUM) / LINE_NUM_EACH_ROW))
				: DEFAULT_GRID_TOP_PECENTAGE);

		if (topInt >= MAX_GRID_TOP_PECENTAGE) {
			topInt = MAX_GRID_TOP_PECENTAGE;
		}

		gridTop = topInt + "%";

		return gridTop;
	}

}
