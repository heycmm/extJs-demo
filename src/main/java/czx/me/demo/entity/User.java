/**
 * Copyright 2010-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package czx.me.demo.entity;

import ch.rasc.extclassgenerator.Model;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 代碼生成器根據註解@ModelField和@Model,可以覆蓋默認生成選項，生成一些附加信息到model對象源碼。
 * 参考来源：https://www.twblogs.net/a/5b85ae082b71775d1cd3a70a
 */
@Data
@Entity
@Table(name = "tb_user")
@Model(value = "Simple.model.User", paging = true, readMethod = "userService.load",
		createMethod = "userService.create", updateMethod = "userService.update",
		destroyMethod = "userService.destroy")
public class User implements Serializable {
	private static final long serialVersionUID = 3060117944880138064L;
	/**
	 * 编号
	 */
	@Id
	@GenericGenerator(name = "123uuid", strategy = "uuid")
	@GeneratedValue(generator = "123uuid")
	private String id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@Email
	private String email;

	private String city;

}
