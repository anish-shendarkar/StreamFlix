"use client"

import { useState } from "react"
import Link from "next/link"
import Image from "next/image"
import { Bell, ChevronDown, ChevronLeft, ChevronRight, Play, Plus, Search } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { cn } from "@/lib/utils"
import Navbar from "@/components/Navbar"

export default function OTTPlatform() {
  const [activeCategory, setActiveCategory] = useState("Home")

  return (
    <div className="min-h-screen bg-black text-white">
      {/* Navigation Bar */}
      {/* <header className="fixed top-0 w-full z-50 bg-gradient-to-b from-black/80 to-transparent backdrop-blur-sm">
        <div className="container flex items-center justify-between py-4">
          <div className="flex items-center gap-8">
            <Link href="/" className="pl-10 text-2xl font-bold text-red-600">
              StreamFlix
            </Link>
            <nav className="hidden md:flex items-center space-x-6">
              {["Home", "TV Shows", "Movies", "New & Popular", "My List"].map((item) => (
                <Link
                  key={item}
                  href="#"
                  className={cn(
                    "text-sm font-medium transition-colors hover:text-white/80",
                    activeCategory === item ? "text-white" : "text-white/70",
                  )}
                  onClick={() => setActiveCategory(item)}
                >
                  {item}
                </Link>
              ))}
            </nav>
            <Button variant="ghost" size="icon" className="md:hidden">
              <ChevronDown className="h-4 w-4" />
              <span className="sr-only">Menu</span>
            </Button>
          </div>
          <div className="flex items-center gap-4">
            <div className="relative hidden md:flex items-center">
              <Search className="absolute left-2.5 h-4 w-4 text-white/70" />
              <Input
                type="search"
                placeholder="Titles, people, genres"
                className="w-[180px] lg:w-[240px] pl-8 bg-black/20 border-white/10 text-sm focus-visible:ring-white/20"
              />
            </div>
            <Button variant="ghost" size="icon" className="md:hidden">
              <Search className="h-5 w-5" />
              <span className="sr-only">Search</span>
            </Button>
            <Button variant="ghost" size="icon">
              <Bell className="h-5 w-5" />
              <span className="sr-only">Notifications</span>
            </Button>
            <Avatar className="h-8 w-8 border border-transparent hover:border-white">
              <AvatarImage src="/placeholder.svg" alt="User" />
              <AvatarFallback className="bg-red-600">U</AvatarFallback>
            </Avatar>
          </div>
        </div>
      </header> */}

      <Navbar />

      <main className="pt-16">
        {/* Hero Section */}
        <section className="relative h-[80vh] w-full">
          <Image
            src="/placeholder.svg?height=1080&width=1920"
            alt="Featured Content"
            fill
            className="object-cover brightness-75"
            priority
          />
          <div className="absolute inset-0 bg-gradient-to-t from-black via-black/20 to-transparent" />
          <div className="absolute bottom-0 left-0 right-0 p-6 md:p-12 space-y-4">
            <h1 className="text-4xl md:text-6xl font-bold max-w-2xl">Stranger Things</h1>
            <p className="text-lg max-w-2xl text-white/90">
              When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying
              supernatural forces, and one strange little girl.
            </p>
            <div className="flex gap-3 pt-2">
              <Button className="gap-2 text-lg px-6 py-6">
                <Play className="h-5 w-5" />
                Play
              </Button>
              <Button variant="secondary" className="gap-2 text-lg px-6 py-6 bg-white/70 hover:bg-white">
                <Plus className="h-5 w-5" />
                My List
              </Button>
            </div>
          </div>
        </section>

        {/* Content Sections */}
        <ContentRow title="Popular on StreamFlix" />
        <ContentRow title="Continue Watching" />
        <ContentRow title="Trending Now" />
        <ContentRow title="New Releases" />
        <ContentRow title="Action & Adventure" />
      </main>

      {/* Footer */}
      <footer className="py-12 px-6 bg-black text-white/60">
        <div className="container mx-auto">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-8">
            <div className="space-y-4">
              <h3 className="text-white font-medium">Company</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="hover:underline">
                    About Us
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Jobs
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Contact
                  </Link>
                </li>
              </ul>
            </div>
            <div className="space-y-4">
              <h3 className="text-white font-medium">Help</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="hover:underline">
                    Account
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Plans
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Support
                  </Link>
                </li>
              </ul>
            </div>
            <div className="space-y-4">
              <h3 className="text-white font-medium">Legal</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="hover:underline">
                    Privacy
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Terms
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Cookie Preferences
                  </Link>
                </li>
              </ul>
            </div>
            <div className="space-y-4">
              <h3 className="text-white font-medium">Follow Us</h3>
              <ul className="space-y-2">
                <li>
                  <Link href="#" className="hover:underline">
                    Instagram
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Twitter
                  </Link>
                </li>
                <li>
                  <Link href="#" className="hover:underline">
                    Facebook
                  </Link>
                </li>
              </ul>
            </div>
          </div>
          <div className="mt-12 text-sm">&copy; {new Date().getFullYear()} StreamFlix. All rights reserved.</div>
        </div>
      </footer>
    </div>
  )
}

// Content Row Component
function ContentRow({ title }: { title: string }) {
  return (
    <section className="py-6 px-6">
      <h2 className="text-xl font-medium mb-4">{title}</h2>
      <div className="relative group">
        <div className="flex gap-2 overflow-x-auto pb-4 scrollbar-hide">
          {Array.from({ length: 8 }).map((_, i) => (
            <ContentCard key={i} />
          ))}
        </div>
        <button className="absolute left-0 top-1/2 -translate-y-1/2 bg-black/50 p-2 rounded-full opacity-0 group-hover:opacity-100 transition-opacity">
          <ChevronLeft className="h-6 w-6" />
        </button>
        <button className="absolute right-0 top-1/2 -translate-y-1/2 bg-black/50 p-2 rounded-full opacity-0 group-hover:opacity-100 transition-opacity">
          <ChevronRight className="h-6 w-6" />
        </button>
      </div>
    </section>
  )
}

// Content Card Component
function ContentCard() {
  return (
    <div className="relative group flex-shrink-0 cursor-pointer transition-transform duration-300 hover:scale-105">
      <div className="w-[180px] h-[100px] md:w-[240px] md:h-[135px] overflow-hidden rounded">
        <Image
          src="/placeholder.svg?height=135&width=240"
          width={240}
          height={135}
          alt="Content thumbnail"
          className="object-cover w-full h-full"
        />
      </div>
      <div className="absolute inset-0 bg-black/40 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center gap-2">
        <Button size="icon" variant="ghost" className="rounded-full bg-white/20 hover:bg-white/30">
          <Play className="h-4 w-4" fill="currentColor" />
        </Button>
        <Button size="icon" variant="ghost" className="rounded-full bg-white/20 hover:bg-white/30">
          <Plus className="h-4 w-4" />
        </Button>
      </div>
    </div>
  )
}

