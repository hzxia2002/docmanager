$.fn.xmlTree = function (settings) {
    var o = $.extend({
        expanded:''
    }, settings);

    return $(this).each(function () {
        if (!$(this).parents('.tree').length) {
            var tree = $(this);
            if (!$('body').is('[role]')) {
                $('body').attr('role', 'application');
            }
            tree.attr({'role':'tree'}).addClass('tree');
            tree.find('a:eq(0)').attr('tabindex', '0');
//            tree.find('a:gt(0)').attr('tabindex', '-1');   //从0开始计数
            tree.find('ul').attr('role', 'group').addClass('tree-group-collapsed');
            tree.find('li').attr('role', 'treeitem');
            tree.find('li:has(ul)')
                .attr('aria-expanded', 'false')
                .find('>a')
                .addClass('tree-parent tree-parent-collapsed');
            tree
                .find(o.expanded)
                .attr('aria-expanded', 'true')
                .find('>a')
                .removeClass('tree-parent-collapsed')
                .next()
                .removeClass('tree-group-collapsed');
            tree
                .bind('expand', function (event) {
                    var target = $(event.target) || tree.find('a[tabindex=0]');
                    target.removeClass('tree-parent-collapsed');
                    target.next().hide().removeClass('tree-group-collapsed').slideDown(150, function () {
                        $(this).removeAttr('style');
                        target.parent().attr('aria-expanded', 'true');
                    });
                })
                .bind('collapse', function (event) {
                    var target = $(event.target) || tree.find('a[tabindex=0]');
                    target.addClass('tree-parent-collapsed');
                    target.next().slideUp(150, function () {
                        target.parent().attr('aria-expanded', 'false');
                        $(this).addClass('tree-group-collapsed').removeAttr('style');
                    });
                })
                .bind('toggle', function (event) {
                    var target = $(event.target) || tree.find('a[tabindex=0]');
                    if (target.parent().is('[aria-expanded=false]')) {
                        target.trigger('expand');
                    }
                    else {
                        target.trigger('collapse');
                    }
                })
                .bind('traverseDown', function (event) {
                    var target = $(event.target) || tree.find('a[tabindex=0]');
                    var targetLi = target.parent();
                    if (targetLi.is('[aria-expanded=true]')) {
                        target.next().find('a').eq(0).focus();
                    }
                    else if (targetLi.next().length) {
                        targetLi.next().find('a').eq(0).focus();
                    }
                    else {
                        targetLi.parents('li').next().find('a').eq(0).focus();
                    }
                })
                .bind('traverseUp', function (event) {
                    var target = $(event.target) || tree.find('a[tabindex=0]');
                    var targetLi = target.parent();
                    if (targetLi.prev().length) {
                        if (targetLi.prev().is('[aria-expanded=true]')) {
                            targetLi.prev().find('li:visible:last a').eq(0).focus();
                        }
                        else {
                            targetLi.prev().find('a').eq(0).focus();
                        }
                    }
                    else {
                        targetLi.parents('li:eq(0)').find('a').eq(0).focus();
                    }
                });
            tree
                .focus(function (event) {
                    tree.find('[tabindex=0]').attr('tabindex', '-1').removeClass('tree-item-active');
                    $(event.target).attr('tabindex', '0').addClass('tree-item-active');
                })
                .click(function (event) {
                    var target = $(event.target);
                    if (target.is('a.tree-parent')) {
                        target.trigger('toggle');
                        target.eq(0).focus();
                        return false;
                    }
                })
                .keydown(function (event) {
                    var target = tree.find('a[tabindex=0]');
                    if (event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40) {
                        if (event.keyCode == 37) {
                            if (target.parent().is('[aria-expanded=true]')) {
                                target.trigger('collapse');
                            }
                            else {
                                target.parents('li:eq(1)').find('a').eq(0).focus();
                            }
                        }
                        if (event.keyCode == 39) {
                            if (target.parent().is('[aria-expanded=false]')) {
                                target.trigger('expand');
                            }
                            else {
                                target.parents('li:eq(0)').find('li a').eq(0).focus();
                            }
                        }
                        if (event.keyCode == 38) {
                            target.trigger('traverseUp');
                        }
                        if (event.keyCode == 40) {
                            target.trigger('traverseDown');
                        }
                        return false;
                    }
                    else if ((event.keyCode == 13 || event.keyCode == 32) && target.is('a.tree-parent')) {
                        target.trigger('toggle');
                        return false;
                    }
                });
        }
    });
};				