package com.heimlich.domain.project.controller.fospm1;

import java.util.Map;

import com.heimlich.domain.common.grid.Common2Model;
import com.heimlich.domain.project.controller.BaseController;

public abstract class Fospm1Controller extends BaseController {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用於GRID_TABLE資料
	 */
	protected Common2Model gridModel ;
	
	public interface JsonMapConverter<Po> {
		Map<String, String> tojsonMap(Po po);
	}

	public Common2Model getGridModel() {
		return gridModel;
	}

	public void setGridModel(Common2Model gridModel) {
		this.gridModel = gridModel;
	}

}
