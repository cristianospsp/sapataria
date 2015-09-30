



var onLoadMasks = function(){
    
    
    onLoadFoneDDDMask();
    onLoadFoneMask();
    
    $('.cpf').mask('000.000.000-00');
    $('.cep').mask('00000-000');
    $('.cnpj').mask('00.000.000/0000-00');
    $('.ie_sp').mask('000.000.000.000');
    $('.ip').mask('099.099.099.099');
    $('.hora_minuto').mask('00:00');
    
    //adicionar outras mascaras aqui
};

var onloadHtml5Input = function(){
    if(!Modernizr.inputtypes.date){
        var $input = $('input[type=date]');
        $input.datepicker({
            dateFormat : 'dd/mm/yy',
            showOn : 'bootstrap'
        });

        $input.mask('00/00/0000');
    }


};

var onSwitchCheckboxLoad = function(){
  $('.switch').bootstrapSwitch();
};

function onLoadFoneDDDMask(){
    var maskBehavior = function (val) {
        return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
    },
    options = {onKeyPress: function (val, e, field, options) {
            field.mask(maskBehavior.apply({}, arguments), options);
        }
    };

    $('.tel_ddd').mask(maskBehavior,options);
}

function onLoadFoneMask(){
    var maskBehavior = function (val) {
        return val.replace(/\D/g, '').length === 9 ? '00000-0000' : '0000-00009';
    },
    options = {onKeyPress: function (val, e, field, options) {
            field.mask(maskBehavior.apply({}, arguments), options);
        }
    };

    $('.tel').mask(maskBehavior,options);
}

function closeDialogIfSucess(xhr, status, args, dialogWidget, dialogId) {
    if (args.validationFailed || args.KEEP_DIALOG_OPENED) {
        jQuery('#'+dialogId).effect("bounce", {times : 4, distance : 20}, 100);
    } 
    else {
    dialogWidget.hide();
    }
}