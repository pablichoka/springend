package com.kCalControl.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kCalControl.model.Assets;

public interface AssetsService {
    ObjectNode returnAssets(Assets assets);
}
