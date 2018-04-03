package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

	public IdwallFormatter(Integer limit) {
		super(limit);
	}

	/**
	 * Should format as described in the challenge
	 *
	 * @param text
	 * @return
	 */
	@Override
	public String format(String text) {
		List<String> lineList = new ArrayList<>();
		StringBuilder result = new StringBuilder();

		int index = 0;

		String[] wordsSplit = text.split(" ");
		StringBuilder lineBuilder = new StringBuilder();

		while (index < wordsSplit.length) {

			if ((lineBuilder.length() + wordsSplit[index].length() + 1) <= limit) {
				lineBuilder.append(" " + wordsSplit[index]);
				index++;
			} else {
				String temp = justifyText(lineBuilder.toString());
				lineList.add(temp);
				result.append(temp + "\n");
				lineBuilder = new StringBuilder();
			}
		}

		System.out.println("Items:");
		lineList.forEach(line -> {
			System.out.printf("[size %d] > [%s] \n", line.length(), line);
		});

		return result.toString();
	}

	private String justifyText(String line) {

		StringBuilder lineBuilder = new StringBuilder(line);

		int total = 0;
		int indexChar = 0;
		int indexStart = 0;

		if (lineBuilder.toString().startsWith(" "))
			lineBuilder = new StringBuilder().append(line.substring(1));
		if (lineBuilder.toString().endsWith(" "))
			lineBuilder = new StringBuilder().append(line.substring(0, lineBuilder.length() - 1));

		if (lineBuilder.length() < limit) {

			total = limit - lineBuilder.length();

			while (total > 0) {
				System.out.println("TOTAL > " + total);
				indexChar = lineBuilder.toString().indexOf(" ", indexStart);
				System.out.println("INDEX > " + indexChar);

				if (indexChar < 0) {
					indexChar = 0;
					indexStart = 0;
				} else if (indexChar < 0) {
					indexChar = 1;
				} else {
					String temp1 = lineBuilder.toString().substring(0, indexChar);
					String temp2 = lineBuilder.toString().substring(indexChar);
					lineBuilder = new StringBuilder().append(temp1).append(" ").append(temp2);
					System.out.println("FINAL > " + lineBuilder.toString() + " | Length = " + lineBuilder.length());
					indexStart = indexChar + 2;
					total--;
				}
			}
		}

		return lineBuilder.toString();
	}
}
