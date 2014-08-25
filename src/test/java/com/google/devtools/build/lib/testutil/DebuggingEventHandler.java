// Copyright 2014 Google Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.testutil;

import com.google.common.collect.ImmutableSet;
import com.google.devtools.build.lib.events.Event;
import com.google.devtools.build.lib.events.EventHandler;
import com.google.devtools.build.lib.events.EventKind;

import java.io.PrintStream;
import java.util.Set;

/**
 * Prints all errors and warnings to {@link System#out}.
 */
public class DebuggingEventHandler implements EventHandler {

  private PrintStream out;

  public DebuggingEventHandler() {
    this.out = System.out;
  }
  @Override
  public Set<EventKind> getEventMask() {
    return ImmutableSet.of();
  }

  @Override
  public void handle(Event e) {
    if (e.getLocation() != null) {
      out.println(e.getKind() + " " + e.getLocation() + ": " + e.getMessage());
    } else {
      out.println(e.getKind() + " " + e.getMessage());
    }
  }

  @Override
  public boolean showOutput(String tag) {
    return true;
  }
}