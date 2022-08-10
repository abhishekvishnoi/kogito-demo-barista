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
package org.kie.kogito.barista.service;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.coffeeshop.Order;

@ApplicationScoped
public class FoodOrderService {

    public boolean process(Order order) {

        boolean result = Math.random() < 0.9;

        if (result) {

            System.out.println("*******************************************************");
            System.out.println("Food is Ready , Please collect  from counter .!!");
            System.out.println("*******************************************************");
        } else {
            System.out.println("*******************************************************");
            System.out.println("Food cannot be prepared . Ask for refund!!");
            System.out.println("*******************************************************");

        }

        return result;
    }

}
