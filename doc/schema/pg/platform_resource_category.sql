-- 平台资源分类表
CREATE TABLE platform_resource_category (
    -- 主键
                               id BIGSERIAL PRIMARY KEY,
    -- 父级编号
                               parent_id BIGINT DEFAULT NULL,
    -- 编码
                               code VARCHAR(64) NOT NULL,
    -- 名称
                               name VARCHAR(64) NOT NULL,
    -- 显示顺序
                               show_order SMALLINT NOT NULL DEFAULT 1,
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
COMMENT ON COLUMN platform_resource_category.id IS '主键（自增ID，内部使用）';
COMMENT ON COLUMN platform_resource_category.parent_id IS '父级编号';
COMMENT ON COLUMN platform_resource_category.code IS '编码';
COMMENT ON COLUMN platform_resource_category.name IS '名称';
COMMENT ON COLUMN platform_resource_category.show_order IS '显示顺序';
COMMENT ON COLUMN platform_resource_category.remark IS '备注';
COMMENT ON COLUMN platform_resource_category.creator_id IS '创建人账户ID';
COMMENT ON COLUMN platform_resource_category.created_by IS '创建人姓名';
COMMENT ON COLUMN platform_resource_category.created_time IS '创建时间';
COMMENT ON COLUMN platform_resource_category.updater_id IS '更新人账户ID';
COMMENT ON COLUMN platform_resource_category.updated_by IS '更新人姓名';
COMMENT ON COLUMN platform_resource_category.updated_time IS '更新时间';
COMMENT ON COLUMN platform_resource_category.version IS '乐观锁版本号';

-- ========== 表注释 ==========
COMMENT ON TABLE platform_resource_category IS '平台资源分类表';

-- ========== 建议添加的索引 ==========

-- 1. 分组编码唯一索引（业务主键）
CREATE UNIQUE INDEX IF NOT EXISTS uk_resource_category_code ON platform_resource_category(code);

