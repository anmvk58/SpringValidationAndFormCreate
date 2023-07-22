package com.anmv.entity;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class AccountDTO {
    @NonNull
    private int id;

    @NonNull
    private String username;

    @NonNull
    private String departmentName;
}
