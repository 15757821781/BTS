$(document).ready(function() {
	//查询
    $('#fileentry_search').click(function() {
		$("#fileinfomodal").modal('hide');
		$('#filemanagetable').bootstrapTable('refresh');
	});
})
