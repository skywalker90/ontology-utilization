package com.ontology.utilization.parser.dataset;

import java.io.FileNotFoundException;

import com.ontology.utilization.parser.dataset.model.Dataset;

public interface DatasetParser {

	public Dataset getParsedDataset() throws Exception;

}
