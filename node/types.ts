/** 视图树节点 */
export type ViewTree = {
    deep: number;
    index: number;
    resource_id: string;
    resource_id_name: string;
    text: string;
    /** 屏幕坐标 "[0,0][1080,2316]" */
    bounds: string;
    class: string;
    inputType: number;
    isEditable: boolean;
    isClickable: boolean;
    isCheckable: boolean;
    isChecked: boolean;
    isVisibleToUser: boolean;
    isAccessibilityFocused: boolean;
    windowId: number;
    viewId: number;
    childrens: ViewTree[];
}

/** 视图匹配回调 */
export type MatchCallback = (view: ViewTree) => boolean

/** Controller 连接配置 */
export type Options = {
    /** default : repl */
    type?: 'repl' | 'network';
    /** default : json */
    command_type?: 'text' | 'json';
    /** default : monkey-repl */
    name?: string;
    /** default : 5678 */
    port?: number;
    /** default : '' */
    ip_address?: string;
    /** default : 192.168.* */
    allow_ip_address?: string;
    /** default : true */
    query_view?: boolean;
    /** default : true */
    activity_controller?: boolean;
    /** default : false，跳过推送 jar */
    skipPush?: boolean;
    /** default : false，打印 stderr 调试日志 */
    debug?: boolean;
}

/** exec 命令返回结果 */
export type ExecResult = {
    id: number;
    isSuccess: boolean;
    data: string;
}
