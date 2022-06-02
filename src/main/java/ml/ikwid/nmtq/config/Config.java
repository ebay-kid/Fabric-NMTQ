package ml.ikwid.nmtq.config;

import ml.ikwid.nmtq.NMTQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Config {
	private Scanner input;

	private final HashMap<String, String> entries;

	public Config(String fileName, String defaultConfig) {
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			NMTQ.LOGGER.info("File not found");

			try {
				File config = new File(fileName);
				if(!config.getParentFile().exists()) {
					config.getParentFile().mkdirs();
				}
				config.createNewFile();

				File file = new File(config.getAbsolutePath());
				FileWriter writer = new FileWriter(config);
				writer.write(defaultConfig);

				writer.close();
				input = new Scanner(new File(fileName));


			} catch(IOException er) {
				NMTQ.LOGGER.info(er);
			}
		} finally {
			String lines = this.getLines();
			this.entries = this.getEntries(lines);
		}
	}

	/**
	 * Whether getting Scanner was successful
	 * @return true if successful, false otherwise
	 */
	public boolean successful() {
		return input != null;
	}

	/**
	 * Get the lines of the file
	 *
	 * Format of file:
	 *
	 * # This is a comment
	 *
	 * name=value
	 * name2=value2
	 *
	 * and so on. This will therefore remove the line breaks and replace with an equals, and {@link #getEntries(String)}
	 * will return a HashMap with the names mapped to entries.
	 *
	 * @return the lines of the file, as a single string without line breaks. Every item is separated by an equals.
	 */
	private String getLines() {
		StringBuilder sb = new StringBuilder();

		while (input.hasNextLine()) {
			String line = input.nextLine();
			if(line == null || Objects.equals(line, "") || line.length() == 0 || line.charAt(0) == '#') continue;
			NMTQ.LOGGER.info("line: " + line);
			sb.append(line);
			sb.append("=");
		}
		this.input.close();
		return sb.toString();
	}

	/**
	 * Get the entries from the formatted string
	 *
	 * @param lines the formatted string from {@link #getLines()}, or another source if you so wish
	 * @return a HashMap with the names mapped to entries
	 */
	private HashMap<String, String> getEntries(String lines) {
		HashMap<String, String> entries = new HashMap<>();

		String[] linesArray = lines.split("="); // I've been duly informed by stackoverflow that no regex will be created since it's single char
		for (int i = 0; i < linesArray.length; i += 2) {
			entries.put(linesArray[i], linesArray[i + 1]); // should be slightly faster than double split. should be.
		}

		return entries;
	}

	public String getValue(String key) {
		return this.entries.get(key);
	}
}
