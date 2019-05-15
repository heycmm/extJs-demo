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
package czx.me.demo.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;
import czx.me.demo.base.AbstractCrudService;
import czx.me.demo.entity.User;
import czx.me.demo.repository.UserRepository;
import czx.me.demo.utils.PropertyComparatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *   proxy : {
 *     type : "direct",
 *     api : {
 *       read : userService.load,
 *       create : userService.create,
 *       update : userService.update,
 *       destroy : userService.destroy
 *     },
 *     reader : {
 *       rootProperty : "records"
 *     },
 *     writer : {
 *       writeAllFields : true
 *     }
 *   }
 */
@Slf4j
@Service
public class UserService extends AbstractCrudService<User, Long>  {

	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
	/**
	 * 负责处理从DirectStore发出的创建，更新和销毁请求的方法添加注解
	 * @ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY)。
	 * 此类方法可以有一个集合类型参数或单个对象。
	 *
	 * 不能两个名称一样的方法都用@ExtDirectMethod注解。
	 *
	 * 方法的返回类型跟参数类型相同或是ExtDirectStoreResult实例。当reader配置了root属性时方法返回一个对象实例。
	 * @param request
	 * @return
	 */
	@ExtDirectMethod(value = ExtDirectMethodType.STORE_READ, group = "simpleapp")
	public ExtDirectStoreResult<User> load(ExtDirectStoreReadRequest request) {

		StringFilter filter = request.getFirstFilterForField("filter");
		Stream<User> usersStream;
		int totalSize;
		if (filter == null || StringUtils.isEmpty(filter.getValue())) {
			//Collection<User> users = this.userDb.getAll();
			List<User> users = userRepository.findAll();
			totalSize = users.size();
			usersStream = users.stream();
			log.info("UserService～～～～全部查询");
		}
		else {
			List<User> users = userRepository.findByLastNameContainingOrFirstNameContaining(filter.getValue(), filter.getValue());
			//List<User> users = this.userDb.filter(filter.getValue());
			totalSize = users.size();
			usersStream = users.stream();
			log.info("UserService～～～～执行搜索框查询操作");

		}

		Comparator<User> comparator = PropertyComparatorFactory
				.createComparatorFromSorters(request.getSorters());
		if (comparator != null) {
			usersStream = usersStream.sorted(comparator);

		}

		if (request.getPage() != null && request.getLimit() != null) {
			log.info("UserService～～～～分页控件的操作");
			usersStream = usersStream.skip(request.getStart()).limit(request.getLimit());
		}

		return new ExtDirectStoreResult<>(totalSize,
				usersStream.collect(Collectors.toList()), Boolean.TRUE);
	}
	@ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, group = "simpleapp")
	public List<User> create(List<User> newUsers) {
		List<User> insertedUsers = new ArrayList<>();

		for (User newUser : newUsers) {
			this.create(newUser);
			insertedUsers.add(newUser);
		}
		log.info("UserService～～～～执行新增操作");
		return insertedUsers;
	}
	@ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, group = "simpleapp")
	public List<User> update(List<User> modifiedUsers) {
		List<User> updatedRecords = new ArrayList<>();
		for (User modifiedUser : modifiedUsers) {

			//User u = this.userDb.findUser(modifiedUser.getId());
			User update = this.update(modifiedUser);
			//u.update(modifiedUser);
				updatedRecords.add(update);

		}
		log.info("UserService～～～～执行修改操作");
		return updatedRecords;
	}
	@ExtDirectMethod(value = ExtDirectMethodType.STORE_MODIFY, group = "simpleapp")
	public void destroy(List<User> destroyUsers) {
		for (User user : destroyUsers) {
			this.remove(user);
		}
		log.info("UserService～～～～执行删除操作");
	}
}
