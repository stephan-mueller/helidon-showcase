/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.openknowledge.projects.greet;

import io.helidon.microprofile.server.Server;

public final class Main {

  private Main() {
    super();
  }

  public static void main(final String... args) {
    Server server = startServer();
    System.out.println("http://localhost:" + server.port() + "/api/greet");
  }

  static Server startServer() {
    return Server.create().start();
  }
}