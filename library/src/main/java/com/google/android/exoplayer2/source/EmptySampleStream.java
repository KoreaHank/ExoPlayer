/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;

/**
 * A dummy {@link SampleStream} that has a {@link Format} but has no samples, meaning the end of the
 * stream is immediately reached.
 */
public final class EmptySampleStream implements SampleStream {

  private final Format sampleFormat;

  public EmptySampleStream(Format sampleFormat) {
    this.sampleFormat = sampleFormat;
  }

  @Override
  public boolean isReady() {
    return true;
  }

  @Override
  public void maybeThrowError() throws IOException {
    // Do nothing.
  }

  @Override
  public int readData(FormatHolder formatHolder, DecoderInputBuffer buffer) {
    if (buffer == null) {
      formatHolder.format = sampleFormat;
      return C.RESULT_FORMAT_READ;
    }
    buffer.setFlags(C.BUFFER_FLAG_END_OF_STREAM);
    return C.RESULT_BUFFER_READ;
  }

  @Override
  public void skipToKeyframeBefore(long timeUs) {
    // Do nothing.
  }

}