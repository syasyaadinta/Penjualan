package com.tes.Penjualan.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
@Getter
@Setter
public class TransHeaderId implements Serializable {

    @Schema(description = "Document Number PK.")
    @Column(name = "DocumentCode", nullable = false, length = 3)
    private String documentCode;

    @Schema(description = "Document Number PK.")
    @Column(name = "DocumentNumber", nullable = false, length = 10)
    private String documentNumber;

    @Override
    public int hashCode() {
        return Objects.hash(documentCode, documentNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TransHeaderId that = (TransHeaderId) obj;
        return Objects.equals(documentCode, that.documentCode)
                && Objects.equals(documentNumber, that.documentNumber);
    }

    @Override
    public String toString() {
        return "TransHeaderId{" +
                "documentCode='" + documentCode + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}
