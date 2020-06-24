function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}
function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        PF('dlg').jq.effect("shake", {times: 5}, 100);
    } else {
        PF('dlg').hide();
        $('#loginLink').fadeOut();
    }
}