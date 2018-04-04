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

		// Cria array de paragrafos
		String[] paragraphs = text.split("\n");
		StringBuilder paragraphFinal = new StringBuilder();

		// Para cada parágrafo, justify
		for (int i = 0; i < paragraphs.length; i++) {

			// Cria array de palavras
			String[] wordsSplit = paragraphs[i].split(" ");
			StringBuilder lineBuilder = new StringBuilder();
			int index = 0;

			while (index < wordsSplit.length) {

				// Monta linha de max length = 40
				if ((lineBuilder.length() + wordsSplit[index].length() + 1) <= limit) {

					if (lineBuilder.length() == 0)
						lineBuilder.append(wordsSplit[index]);
					else
						lineBuilder.append(" " + wordsSplit[index]);

					index++;
				} else {
					String temp = justifyText(lineBuilder.toString());
					lineList.add(temp);
					paragraphFinal.append(temp + "\n");
					lineBuilder = new StringBuilder();
				}

				if (index == wordsSplit.length && lineBuilder.length() > 0) {
					String temp = justifyText(lineBuilder.toString());
					lineList.add(temp);
					paragraphFinal.append(temp);
					lineBuilder = new StringBuilder();
				}
			}
			result.append(paragraphFinal).append("\n");
			paragraphFinal = new StringBuilder();
		}
		return result.toString();
	}

	/**
	 * Insere um espaço (\\s) para cada espaço entre letras que encontrar (index
	 * crescente) até que a linha tenha 40 characteres.
	 * 
	 * @param line
	 * @return line justified
	 */
	private String justifyText(String line) {

		StringBuilder lineBuilder = new StringBuilder(line.trim());

		int total = 0;
		int indexChar = 0;
		int indexStart = 0;

		if (lineBuilder.length() < limit) {

			total = limit - lineBuilder.length();

			while (total > 0) {

				indexChar = lineBuilder.toString().indexOf(" ", indexStart);

				if (indexChar < 0) {
					indexChar = 0;
					indexStart = 0;
				} else {
					lineBuilder = new StringBuilder().append(lineBuilder.toString().substring(0, indexChar)).append(" ")
							.append(lineBuilder.toString().substring(indexChar));
					indexStart = indexChar + 2;
					total--;
				}
			}
		}
		return lineBuilder.toString();
	}
}
