CREATE TABLE audit_logs (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            operation VARCHAR(255) NOT NULL,
                            details TEXT,
                            timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_audit_timestamp ON audit_logs(timestamp);

CREATE INDEX idx_audit_operation ON audit_logs(operation);