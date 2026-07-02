-- 平台服务定义表
CREATE TABLE platform_service_instance (
    -- 编号
                               id BIGSERIAL PRIMARY KEY,
    -- 编码
                               code VARCHAR(64) NOT NULL,
    -- 编码
                               context_path VARCHAR(512) NOT NULL,
    -- 状态
                               status SMALLINT NOT NULL DEFAULT 1,
    -- 名称
                               name VARCHAR(128) NOT NULL,
    -- 备注
                               remark VARCHAR(512) DEFAULT NULL,
    -- 审计字段：创建人编号
                               creator_id VARCHAR(64) NOT NULL,
    -- 审计字段：创建人
                               created_by VARCHAR(64) NOT NULL,
    -- 审计字段：创建时间
                               created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- 审计字段：更新人编号
                               updater_id VARCHAR(64) DEFAULT NULL,
    -- 审计字段：更新人
                               updated_by VARCHAR(64) DEFAULT NULL,
    -- 审计字段：更新时间
                               updated_time TIMESTAMP DEFAULT NULL,
    -- 版本号
                               version INTEGER DEFAULT 1
);

-- ========== platform_service_instance 表字段注释 ==========
COMMENT ON COLUMN platform_service_instance.id IS '编号';
COMMENT ON COLUMN platform_service_instance.code IS '编码';
COMMENT ON COLUMN platform_service_instance.context_path IS '路径';
COMMENT ON COLUMN platform_service_instance.status IS '状态';
COMMENT ON COLUMN platform_service_instance.name IS '名称';
COMMENT ON COLUMN platform_service_instance.remark IS '备注';
COMMENT ON COLUMN platform_service_instance.creator_id IS '创建人编号';
COMMENT ON COLUMN platform_service_instance.created_by IS '创建人';
COMMENT ON COLUMN platform_service_instance.created_time IS '创建时间';
COMMENT ON COLUMN platform_service_instance.updater_id IS '更新人编号';
COMMENT ON COLUMN platform_service_instance.updated_by IS '更新人';
COMMENT ON COLUMN platform_service_instance.updated_time IS '更新时间';
COMMENT ON COLUMN platform_service_instance.version IS '版本号';

-- ========== 表注释 ==========
COMMENT ON TABLE platform_service_instance IS '平台服务定义表';

ALTER TABLE platform_service_instance
    ADD CONSTRAINT uk_platform_service_instance_code UNIQUE (code);