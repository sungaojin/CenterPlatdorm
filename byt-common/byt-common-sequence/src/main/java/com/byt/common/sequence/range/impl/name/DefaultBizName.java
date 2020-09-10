package com.byt.common.sequence.range.impl.name;

import com.byt.common.sequence.range.BizName;
import lombok.AllArgsConstructor;

/**
 * @author liyang
 * @date 2020-02-26
 * 根据传入返回bizname
 */
@AllArgsConstructor
public class DefaultBizName implements BizName {

	private String bizName;

	/**
	 * 生成空间名称
	 */
	@Override
	public String create() {
		return bizName;
	}

}
