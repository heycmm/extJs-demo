Ext.define('Simple.view.user.Edit', {
	extend: 'Ext.window.Window',

	layout: 'fit',
	autoShow: true,
	resizable: false,
	modal: true,
	modelValidation: true,

	items: [ {
		xtype: 'form',
		padding: '5 5 0 5',
		defaults: {
			width: 500,
			msgTarget: 'side',
			xtype: 'textfield'
		},
		items: [ {
			bind: '{editUser.firstName}',
			fieldLabel: '姓氏'
		}, {
			bind: '{editUser.lastName}',
			fieldLabel: '名称'
		}, {
			bind: '{editUser.email}',
			fieldLabel: '邮箱'
		}, {
			bind: '{editUser.city}',
			fieldLabel: '城市'
		} ]
	} ],

	buttons: [ {
		text: 'Save',
		handler: 'saveUser',
		bind: {
			disabled: '{!editUserValid}'
		}
	}, {
		text: 'Cancel',
		handler: 'closeWindow'
	} ]

});