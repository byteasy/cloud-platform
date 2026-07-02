-- 平台资源分组定义表（平台级）
CREATE TABLE platform_resource_group (
    -- 主键
                               id BIGSERIAL PRIMARY KEY,
    -- 资源分类
                               category_id BIGINT NOT NULL,
    -- 编码
                               code VARCHAR(64) NOT NULL,
    -- 名称
                               name VARCHAR(64) NOT NULL,
    -- 显示顺序
                               show_order SMALLINT NOT NULL DEFAULT 1,
    -- 路由
                               router VARCHAR(256) NOT NULL,
    -- 快捷菜单
                               navbar_menu SMALLINT NOT NULL DEFAULT 0,
    -- 是否微应用
                               is_microapp SMALLINT NOT NULL DEFAULT 0,
    -- 入口地址
                               entry VARCHAR(512) DEFAULT NULL,
    -- 拓展属性
                               props VARCHAR(512) DEFAULT NULL,
    -- 备注
                               remark VARCHAR(512) DEFAULT NULL,
    -- 审计字段：创建人账户ID
                               creator_id VARCHAR(64) NOT NULL,
    -- 审计字段：创建人（用户ID）
                               created_by VARCHAR(64) NOT NULL,
    -- 审计字段：创建时间
                               created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- 审计字段：更新人账户ID
                               updater_id VARCHAR(64) DEFAULT NULL,
    -- 审计字段：更新人姓名
                               updated_by VARCHAR(64) DEFAULT NULL,
    -- 审计字段：更新时间
                               updated_time TIMESTAMP DEFAULT NULL,
    -- 乐观锁
                               version INTEGER DEFAULT 1
);
-- ========== platform_resource_group 表字段注释 ==========
COMMENT ON COLUMN platform_resource_group.id IS '主键（自增ID，内部使用）';
COMMENT ON COLUMN platform_resource_group.category_id IS '资源分类';
COMMENT ON COLUMN platform_resource_group.code IS '编码';
COMMENT ON COLUMN platform_resource_group.name IS '名称';
COMMENT ON COLUMN platform_resource_group.show_order IS '显示顺序';
COMMENT ON COLUMN platform_resource_group.router IS '路由';
COMMENT ON COLUMN platform_resource_group.navbar_menu IS '快捷菜单';
COMMENT ON COLUMN platform_resource_group.is_microapp IS '是否微应用';
COMMENT ON COLUMN platform_resource_group.entry IS '入口地址';
COMMENT ON COLUMN platform_resource_group.props IS '拓展属性';
COMMENT ON COLUMN platform_resource_group.remark IS '备注';
COMMENT ON COLUMN platform_resource_group.creator_id IS '创建人账户ID';
COMMENT ON COLUMN platform_resource_group.created_by IS '创建人姓名';
COMMENT ON COLUMN platform_resource_group.created_time IS '创建时间';
COMMENT ON COLUMN platform_resource_group.updater_id IS '更新人账户ID';
COMMENT ON COLUMN platform_resource_group.updated_by IS '更新人姓名';
COMMENT ON COLUMN platform_resource_group.updated_time IS '更新时间';
COMMENT ON COLUMN platform_resource_group.version IS '乐观锁版本号';

-- ========== 表注释 ==========
COMMENT ON TABLE platform_resource_group IS '平台资源分组定义表（用于对资源进行分类管理，如：系统管理、业务管理、报表管理等）';

-- ========== 建议添加的索引 ==========

-- 1. 分组编码唯一索引（业务主键）
CREATE UNIQUE INDEX IF NOT EXISTS uk_resource_group_code ON platform_resource_group(code);

