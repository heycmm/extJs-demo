Ext.define('Simple.view.user.List', {
	extend: 'Ext.grid.Panel',
	requires: [ 'Simple.view.user.UserController', 'Simple.view.user.UserModel' ],

	controller: {
		xclass: 'Simple.view.user.UserController'
	},
	viewModel: {
		xclass: 'Simple.view.user.UserModel'
	},

	bind: {
		store: '{userStore}'
	},

	listeners: {
		itemdblclick: 'editUser'
	},

	title: '用户管理MVC案例',
	reference: 'userGrid',

	columns: [  {
		xtype: 'rownumberer',
		width: 30
	}, {
		header: '姓氏',
		dataIndex: 'firstName',
		flex: 1
	}, {
		header: '名称',
		dataIndex: 'lastName',
		flex: 1
	}, {
		header: '邮箱',
		dataIndex: 'email',
		flex: 1
	}, {
		header: '城市',
		dataIndex: 'city',
		flex: 1
	} ],

	dockedItems: [ {
		xtype: 'toolbar',
		dock: 'top',
		items: [ {
			text: '新增用户',
			iconCls: 'icon-add',
			handler: 'createUser'
		}, {
			text: '删除用户',
			handler: 'deleteUser',
			bind: {
				disabled: '{!userSelected}'
			},
			iconCls: 'icon-delete'
		}, '->', {
			fieldLabel: '查询',
			labelWidth: 40,
			xtype: 'textfield',
			listeners: {
				change: {
					fn: 'filterChange',
					buffer: 350
				}
			}
		} ]
	}, {
		xtype: 'pagingtoolbar',
		dock: 'bottom',
		reference: 'pagingtoolbar'
	} ]

});