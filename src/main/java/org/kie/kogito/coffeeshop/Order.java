/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.coffeeshop;

import java.io.Serializable;

public class Order implements Serializable {

    private String item;

    private String email;

    private String customer;

    private Boolean cardPayment;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order [cardPayment=" + cardPayment + ", customer=" + customer + ", email=" + email + ", item=" + item
                + "]";
    }

    public Order(String item, String email, String customer, Boolean cardPayment) {
        this.item = item;
        this.email = email;
        this.customer = customer;
        this.cardPayment = cardPayment;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Boolean isCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(Boolean cardPayment) {
        this.cardPayment = cardPayment;
    }

}
