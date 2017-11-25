package com.cyb.spring.boot.demo.domain.validation;

import javax.validation.GroupSequence;

/**
 * 保存或更新验证F
 * @author Administrator
 *
 */
@GroupSequence({ Saving.class, Updating.class })
public interface SavingUpdating {

}
