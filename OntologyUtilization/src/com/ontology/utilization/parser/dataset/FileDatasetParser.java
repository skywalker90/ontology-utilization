package com.ontology.utilization.parser.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.parser.dataset.model.Dataset;

public class FileDatasetParser implements DatasetParser {

	private File source;

	public FileDatasetParser(File source) {
		this.source = source;
	}

	@Override
	public Dataset getParsedDataset() throws FileNotFoundException, IOException {
		Dataset dataset = new Dataset();
		List<String> listOfParsedLine = new LinkedList<String>();
		if (source.isFile()) {
			FileReader fr = new FileReader(source);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				listOfParsedLine.add(line);
			}
			br.close();
			fr.close();
		} else {
			throw new FileNotFoundException("File is directory.");
		}
		dataset.setDataset(listOfParsedLine);
		return dataset;
	}
}
